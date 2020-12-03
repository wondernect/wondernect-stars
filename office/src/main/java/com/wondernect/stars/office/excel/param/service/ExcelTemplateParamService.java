package com.wondernect.stars.office.excel.param.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.office.excel.dto.param.BatchAddExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.param.model.ExcelTemplateParam;
import com.wondernect.stars.office.excel.property.model.ExcelBeanProperty;
import com.wondernect.stars.office.excel.property.service.ExcelBeanPropertyService;
import com.wondernect.stars.office.excel.template.model.ExcelTemplate;
import com.wondernect.stars.office.excel.template.service.ExcelTemplateService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * excel导入导出模板配置服务
 *
 * @author chenxun 2020-11-17 16:20:19
 **/
@Service
public class ExcelTemplateParamService extends ExcelTemplateParamAbstractService {

    @Autowired
    private ExcelBeanPropertyService excelBeanPropertyService;

    @Autowired
    private ExcelTemplateService excelTemplateService;

    public void batchAdd(BatchAddExcelTemplateParamRequestDTO batchAddExcelTemplateParamRequestDTO) {
        ExcelTemplate excelTemplate = excelTemplateService.findEntityById(batchAddExcelTemplateParamRequestDTO.getTemplateId());
        if (ESObjectUtils.isNull(excelTemplate)) {
            throw new BusinessException("模板不存在");
        }
        if (CollectionUtils.isNotEmpty(batchAddExcelTemplateParamRequestDTO.getBeanPropertyIdList())) {
            for (String beanPropertyId : batchAddExcelTemplateParamRequestDTO.getBeanPropertyIdList()) {
                ExcelBeanProperty excelBeanProperty = excelBeanPropertyService.findEntityById(beanPropertyId);
                if (ESObjectUtils.isNotNull(excelBeanProperty)) {
                    super.saveEntity(
                            new ExcelTemplateParam(
                                    excelTemplate.getId(),
                                    excelBeanProperty.getBeanId(),
                                    excelBeanProperty.getName(),
                                    excelBeanProperty.getOrderNum()
                            )
                    );
                }
            }
        }
    }
}