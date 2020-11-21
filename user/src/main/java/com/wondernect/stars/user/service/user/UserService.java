package com.wondernect.stars.user.service.user;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelImportDataHandler;
import com.wondernect.elements.easyoffice.excel.ESExcelImportVerifyHandler;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.stars.office.excel.dto.bean.ExcelBeanResponseDTO;
import com.wondernect.stars.office.excel.dto.bean.SaveExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.property.SaveExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.template.ExcelTemplateResponseDTO;
import com.wondernect.stars.office.feign.excel.bean.ExcelBeanServerService;
import com.wondernect.stars.office.feign.excel.param.ExcelTemplateParamServerService;
import com.wondernect.stars.office.feign.excel.property.ExcelBeanPropertyServerService;
import com.wondernect.stars.office.feign.excel.template.ExcelTemplateServerService;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.excel.*;
import com.wondernect.stars.user.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserService
 * Author: chenxun
 * Date: 2020-06-25 19:33
 * Description:
 */
@Service
public class UserService extends UserAbstractService {

    @Autowired
    private ExcelBeanServerService excelBeanServerService;

    @Autowired
    private ExcelBeanPropertyServerService excelBeanPropertyServerService;

    @Autowired
    private ExcelTemplateServerService excelTemplateServerService;

    @Autowired
    private ExcelTemplateParamServerService excelTemplateParamServerService;

    @Autowired
    private UserImportDataHandler userImportDataHandler;

    @Autowired
    private UserImportVerifyHandler userImportVerifyHandler;

    public List<ESExcelItem> excelItemList() {
        List<ESExcelItem> excelItemList = super.excelItemList(UserResponseDTO.class);
        if (CollectionUtils.isNotEmpty(excelItemList)) {
            ESExcelItem excelItemOne = excelItemList.get(0);
            ExcelBeanResponseDTO excelBeanResponseDTO = excelBeanServerService.detailByBean(excelItemOne.getEntity());
            if (ESObjectUtils.isNull(excelBeanResponseDTO)) {
                excelBeanResponseDTO = excelBeanServerService.create(
                        new SaveExcelBeanRequestDTO(
                                excelItemOne.getEntity(),
                                excelItemOne.getEntityName()
                        )
                );
            } else {
                excelBeanResponseDTO = excelBeanServerService.update(
                        excelBeanResponseDTO.getId(),
                        new SaveExcelBeanRequestDTO(
                                excelItemOne.getEntity(),
                                excelItemOne.getEntityName()
                        )
                );
            }
            for (ESExcelItem excelItem : excelItemList) {
                excelBeanPropertyServerService.create(
                        new SaveExcelBeanPropertyRequestDTO(
                                excelBeanResponseDTO.getId(),
                                excelItem.getName(),
                                excelItem.getType(),
                                excelItem.getTitle(),
                                excelItem.getOrderNum(),
                                excelItem.getGetMethodName(),
                                excelItem.getSetMethodName()
                        )
                );
            }
        }
        return excelItemList;
    }

    public void excelDataExport(String templateId, ListUserRequestDTO listUserRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        super.excelDataExport(templateId, UserResponseDTO.class, list(listUserRequestDTO), "用户信息导出", "用户信息导出", "用户信息导出", request, response);
    }

    public void excelDataImport(String templateId, InputStream fileInputStream, HttpServletRequest request, HttpServletResponse response) {
        super.excelDataImport(templateId, UserResponseDTO.class, 1, 1, fileInputStream, "用户信息导入错误信息", request, response);
    }

    public void excelDataImportModel(String templateId, HttpServletRequest request, HttpServletResponse response) {
        super.excelDataExport(templateId, UserResponseDTO.class, new ArrayList<>(), "用户信息导入", "用户信息导入", "用户信息导入模板", request, response);
    }

    @Override
    public void excelDataImport(User entity, UserResponseDTO responseDTO) {

    }

    @Override
    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = excelTemplateServerService.detail(templateId);
        if (ESObjectUtils.isNull(excelTemplateResponseDTO)) {
            throw new BusinessException("导入或导出模板不存在");
        }
        List<ExcelTemplateParamResponseDTO> excelTemplateParamResponseDTOList = excelTemplateParamServerService.list(new ListExcelTemplateParamRequestDTO(excelTemplateResponseDTO.getId(), null));
        if (CollectionUtils.isEmpty(excelTemplateParamResponseDTOList)) {
            throw new BusinessException("导入或导出模板配置参数为空");
        }
        List<ESExcelItemHandler> excelItemHandlerList = new ArrayList<>();
        for (ExcelTemplateParamResponseDTO excelTemplateParamResponseDTO : excelTemplateParamResponseDTOList) {
            switch (excelTemplateParamResponseDTO.getName()) {
                case "id":
                {
                    excelItemHandlerList.add(new UserResponseDTOIdHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "userType":
                {
                    excelItemHandlerList.add(new UserResponseDTOUserTypeHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "username":
                {
                    excelItemHandlerList.add(new UserResponseDTOUsernameHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "name":
                {
                    excelItemHandlerList.add(new UserResponseDTONameHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "gender":
                {
                    excelItemHandlerList.add(new UserResponseDTOGenderHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "avatar":
                {
                    excelItemHandlerList.add(new UserResponseDTOAvatarHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "mobile":
                {
                    excelItemHandlerList.add(new UserResponseDTOMobileHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "email":
                {
                    excelItemHandlerList.add(new UserResponseDTOEmailHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "location":
                {
                    excelItemHandlerList.add(new UserResponseDTOLocationHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "remark":
                {
                    excelItemHandlerList.add(new UserResponseDTORemarkHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "roleTypeId":
                {
                    excelItemHandlerList.add(new UserResponseDTORoleTypeIdHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "roleTypeName":
                {
                    excelItemHandlerList.add(new UserResponseDTORoleTypeNameHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "roleId":
                {
                    excelItemHandlerList.add(new UserResponseDTORoleIdHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "roleName":
                {
                    excelItemHandlerList.add(new UserResponseDTORoleNameHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "enable":
                {
                    excelItemHandlerList.add(new UserResponseDTOEnableHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "editable":
                {
                    excelItemHandlerList.add(new UserResponseDTOEditableHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
                case "deletable":
                {
                    excelItemHandlerList.add(new UserResponseDTODeletableHandler(excelTemplateParamResponseDTO.getTitle(), excelTemplateParamResponseDTO.getOrderNum()));
                    break;
                }
            }
        }
        return excelItemHandlerList;
    }

    @Override
    public ESExcelImportDataHandler generateExcelImportDataHandler(String templateId) {
        return userImportDataHandler;
    }

    @Override
    public ESExcelImportVerifyHandler generateExcelImportVerifyHandler(String templateId) {
        return userImportVerifyHandler;
    }
}
