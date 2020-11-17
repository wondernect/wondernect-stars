package com.wondernect.stars.office.excel.template.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.template.ExcelTemplateResponseDTO;
import com.wondernect.stars.office.excel.dto.template.ListExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.PageExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.SaveExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.template.model.ExcelTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * excel导入导出模板服务抽象实现类
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
@Service
public abstract class ExcelTemplateAbstractService extends BaseStringService<ExcelTemplateResponseDTO, ExcelTemplate> implements ExcelTemplateInterface {

    @Transactional
    @Override
    public ExcelTemplateResponseDTO create(SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO) {
//TODO:判断对象是否存在

        ExcelTemplate excelTemplate = new ExcelTemplate();
        ESBeanUtils.copyProperties(saveExcelTemplateRequestDTO, excelTemplate);
        return super.save(excelTemplate);
    }

    @Transactional
    @Override
    public ExcelTemplateResponseDTO update(String id, SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO) {
        ExcelTemplate excelTemplate = super.findEntityById(id);
        if (ESObjectUtils.isNull(excelTemplate)) {
            throw new BusinessException("excel导入导出模板不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveExcelTemplateRequestDTO, excelTemplate);
        return super.save(excelTemplate);
    }

    @Override
    public List<ExcelTemplateResponseDTO> list(ListExcelTemplateRequestDTO listExcelTemplateRequestDTO) {
        Criteria<ExcelTemplate> excelTemplateCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(excelTemplateCriteria, listExcelTemplateRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<ExcelTemplateResponseDTO> page(PageExcelTemplateRequestDTO pageExcelTemplateRequestDTO) {
        Criteria<ExcelTemplate> excelTemplateCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(excelTemplateCriteria, pageExcelTemplateRequestDTO.getPageRequestData());
    }

    @Override
    public ExcelTemplateResponseDTO generate(ExcelTemplate excelTemplate) {
        ExcelTemplateResponseDTO excelTemplateResponseDTO = new ExcelTemplateResponseDTO();
        ESBeanUtils.copyProperties(excelTemplate, excelTemplateResponseDTO);
        return excelTemplateResponseDTO;
    }

    @Override
    public ExcelTemplate generate(ExcelTemplateResponseDTO excelTemplateResponseDTO) {
        ExcelTemplate excelTemplate = new ExcelTemplate();
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(excelTemplateResponseDTO, excelTemplate);
        return excelTemplate;
    }
}