package com.wondernect.stars.user.service.user;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.excel.*;
import com.wondernect.elements.easyoffice.excel.handler.*;
import com.wondernect.stars.office.excel.dto.bean.ExcelBeanResponseDTO;
import com.wondernect.stars.office.excel.dto.bean.SaveExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.property.ExcelBeanPropertyResponseDTO;
import com.wondernect.stars.office.excel.dto.property.SaveExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.template.ExcelTemplateResponseDTO;
import com.wondernect.stars.office.feign.excel.bean.ExcelBeanServerService;
import com.wondernect.stars.office.feign.excel.param.ExcelTemplateParamServerService;
import com.wondernect.stars.office.feign.excel.property.ExcelBeanPropertyServerService;
import com.wondernect.stars.office.feign.excel.template.ExcelTemplateServerService;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.em.Gender;
import com.wondernect.stars.user.em.UserType;
import com.wondernect.stars.user.excel.*;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.service.localauth.UserLocalAuthService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserService
 * Author: chenxun
 * Date: 2020-06-25 19:33
 * Description:
 */
@Service
public class UserService extends UserAbstractService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

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

    @Autowired
    private UserLocalAuthService userLocalAuthService;

    public void initLocalUserExcelItem(boolean forceUpdate) {
        List<ESExcelItem> excelItemList = ESExcelUtils.getAllEntityExcelItem(LocalUserExcelDTO.class);
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
                if (!forceUpdate) {
                    throw new BusinessException("本地用户导入导出对象已完成初始化");
                }
                excelBeanResponseDTO = excelBeanServerService.update(
                        excelBeanResponseDTO.getId(),
                        new SaveExcelBeanRequestDTO(
                                excelItemOne.getEntity(),
                                excelItemOne.getEntityName()
                        )
                );
            }
            for (ESExcelItem excelItem : excelItemList) {
                ExcelBeanPropertyResponseDTO excelBeanPropertyResponseDTO = excelBeanPropertyServerService.detailByBeanIdAndName(excelBeanResponseDTO.getId(), excelItem.getName());
                if (ESObjectUtils.isNull(excelBeanPropertyResponseDTO)) {
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
                } else {
                    excelBeanPropertyServerService.update(
                            excelBeanPropertyResponseDTO.getId(),
                            new SaveExcelBeanPropertyRequestDTO(
                                    excelBeanPropertyResponseDTO.getBeanId(),
                                    excelBeanPropertyResponseDTO.getName(),
                                    excelItem.getType(),
                                    excelItem.getTitle(),
                                    excelItem.getOrderNum(),
                                    excelItem.getGetMethodName(),
                                    excelItem.getSetMethodName()
                            )
                    );
                }

            }
        }
    }

    public void excelDataExport(String templateId, ListUserRequestDTO listUserRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = excelTemplateServerService.detail(templateId);
        if (ESObjectUtils.isNull(excelTemplateResponseDTO)) {
            throw new BusinessException("导出模板不存在");
        }
        super.excelDataExport(templateId, LocalUserExcelDTO.class, list(listUserRequestDTO), excelTemplateResponseDTO.getName(), excelTemplateResponseDTO.getName(), excelTemplateResponseDTO.getName(), request, response);
    }

    public void excelDataImport(String templateId, InputStream fileInputStream, HttpServletRequest request, HttpServletResponse response) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = excelTemplateServerService.detail(templateId);
        if (ESObjectUtils.isNull(excelTemplateResponseDTO)) {
            throw new BusinessException("导入模板不存在");
        }
        super.excelDataImport(templateId, LocalUserExcelDTO.class, userImportDataHandler, userImportVerifyHandler, 1, 1, fileInputStream, excelTemplateResponseDTO.getName() + "错误信息", request, response);
    }

    public void excelDataImportModel(String templateId, HttpServletRequest request, HttpServletResponse response) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = excelTemplateServerService.detail(templateId);
        if (ESObjectUtils.isNull(excelTemplateResponseDTO)) {
            throw new BusinessException("导入模板不存在");
        }
        super.excelDataExport(templateId, LocalUserExcelDTO.class, new ArrayList<>(), excelTemplateResponseDTO.getName(), excelTemplateResponseDTO.getName(), excelTemplateResponseDTO.getName(), request, response);
    }

    @Override
    public void saveExcelEntityData(Map<String, Object> map, List<ESExcelItem> excelItemList) {
        LocalUserExcelDTO localUserExcelDTO = ESExcelUtils.getImportObject(LocalUserExcelDTO.class, map, excelItemList);
        if (ESObjectUtils.isNotNull(localUserExcelDTO)) {
            User user = new User(
                    UserType.LOCAL,
                    localUserExcelDTO.getUsername(),
                    localUserExcelDTO.getName(),
                    ESObjectUtils.isNotNull(localUserExcelDTO.getGender()) ? localUserExcelDTO.getGender() : Gender.UNKNOWN,
                    localUserExcelDTO.getAvatar(),
                    localUserExcelDTO.getMobile(),
                    localUserExcelDTO.getEmail(),
                    localUserExcelDTO.getLocation(),
                    localUserExcelDTO.getRemark(),
                    localUserExcelDTO.getRoleTypeId(),
                    localUserExcelDTO.getRoleId(),
                    ESObjectUtils.isNotNull(localUserExcelDTO.getEnable()) ? localUserExcelDTO.getEnable() : true,
                    ESObjectUtils.isNotNull(localUserExcelDTO.getEditable()) ? localUserExcelDTO.getEditable() : true,
                    ESObjectUtils.isNotNull(localUserExcelDTO.getDeletable()) ? localUserExcelDTO.getDeletable() : true
            );
            user = super.saveEntity(user);
            userLocalAuthService.create(user.getId(), new SaveUserLocalAuthRequestDTO(localUserExcelDTO.getPassword()));
        }
    }

    @Override
    public List<ESExcelItemHandler> generateExcelItemHandlerList(String templateId) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = excelTemplateServerService.detail(templateId);
        if (ESObjectUtils.isNull(excelTemplateResponseDTO)) {
            throw new BusinessException("导入或导出模板不存在");
        }
        List<ExcelTemplateParamResponseDTO> excelTemplateParamResponseDTOList = excelTemplateParamServerService.list(new ListExcelTemplateParamRequestDTO(excelTemplateResponseDTO.getId(), null, null));
        if (CollectionUtils.isEmpty(excelTemplateParamResponseDTOList)) {
            throw new BusinessException("导入或导出模板配置参数为空");
        }
        List<ESExcelItemHandler> excelItemHandlerList = new ArrayList<>();
        for (ExcelTemplateParamResponseDTO excelTemplateParamResponseDTO : excelTemplateParamResponseDTOList) {
            switch (excelTemplateParamResponseDTO.getName()) {
                case "gender":
                {
                    Map<Gender, String> dictionary = new HashMap<>();
                    dictionary.put(Gender.MALE, "男");
                    dictionary.put(Gender.FEMALE, "女");
                    dictionary.put(Gender.UNKNOWN, "未知");
                    excelItemHandlerList.add(
                            new LocalUserExcelGenderItemHandler(
                                    excelTemplateParamResponseDTO.getName(),
                                    excelTemplateParamResponseDTO.getTitle(),
                                    excelTemplateParamResponseDTO.getOrderNum(),
                                    dictionary
                            )
                    );
                    break;
                }
                case "create_time":
                {
                    excelItemHandlerList.add(
                            new ESExcelTimestampItemHandler(
                                    excelTemplateParamResponseDTO.getName(),
                                    excelTemplateParamResponseDTO.getTitle(),
                                    excelTemplateParamResponseDTO.getOrderNum()
                            )
                    );
                    break;
                }
                default:
                {
                    if (ESStringUtils.equals(String.class.getName(), excelTemplateParamResponseDTO.getType())) {
                        excelItemHandlerList.add(
                                new ESExcelStringItemHandler(
                                        excelTemplateParamResponseDTO.getName(),
                                        excelTemplateParamResponseDTO.getTitle(),
                                        excelTemplateParamResponseDTO.getOrderNum()
                                )
                        );
                    } else if (ESStringUtils.equals(Boolean.class.getName(), excelTemplateParamResponseDTO.getType())) {
                        excelItemHandlerList.add(
                                new ESExcelBooleanItemHandler(
                                        excelTemplateParamResponseDTO.getName(),
                                        excelTemplateParamResponseDTO.getTitle(),
                                        excelTemplateParamResponseDTO.getOrderNum()
                                )
                        );
                    }
                }
            }
        }
        return excelItemHandlerList;
    }
}
