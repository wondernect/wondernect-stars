package com.wondernect.stars.office.excel.param.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.PageExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.SaveExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.property.ExcelBeanPropertyResponseDTO;
import com.wondernect.stars.office.excel.param.model.ExcelTemplateParam;
import com.wondernect.stars.office.excel.property.service.ExcelBeanPropertyService;
import com.wondernect.stars.office.excel.template.model.ExcelTemplate;
import com.wondernect.stars.office.excel.template.service.ExcelTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * excel导入导出模板配置服务抽象实现类
 *
 * @author chenxun 2020-11-17 16:20:19
 **/
@Service
public abstract class ExcelTemplateParamAbstractService extends BaseStringService<ExcelTemplateParamResponseDTO, ExcelTemplateParam> implements ExcelTemplateParamInterface {

    @Autowired
    private ExcelBeanPropertyService excelBeanPropertyService;

    @Autowired
    private ExcelTemplateService excelTemplateService;

    @Transactional
    @Override
    public ExcelTemplateParamResponseDTO create(SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO) {
        ExcelTemplate excelTemplate = excelTemplateService.findEntityById(saveExcelTemplateParamRequestDTO.getTemplateId());
        if (ESObjectUtils.isNull(excelTemplate)) {
            throw new BusinessException("模板不存在");
        }
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
        excelTemplateParamCriteria.add(Restrictions.eq("templateId", excelTemplate.getId()));
        excelTemplateParamCriteria.add(Restrictions.eq("name", saveExcelTemplateParamRequestDTO.getName()));
        ExcelTemplateParam excelTemplateParam = super.findOneEntity(excelTemplateParamCriteria, new ArrayList<>());
        if (ESObjectUtils.isNotNull(excelTemplateParam)) {
            throw new BusinessException("模板参数已存在");
        }
        excelTemplateParam = new ExcelTemplateParam();
        ESBeanUtils.copyProperties(saveExcelTemplateParamRequestDTO, excelTemplateParam);
        return super.save(excelTemplateParam);
    }

    @Transactional
    @Override
    public ExcelTemplateParamResponseDTO update(String id, SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO) {
        ExcelTemplateParam excelTemplateParam = super.findEntityById(id);
        if (ESObjectUtils.isNull(excelTemplateParam)) {
            throw new BusinessException("excel导入导出模板配置不存在");
        }
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
        excelTemplateParamCriteria.add(Restrictions.eq("templateId", saveExcelTemplateParamRequestDTO.getTemplateId()));
        excelTemplateParamCriteria.add(Restrictions.eq("name", saveExcelTemplateParamRequestDTO.getName()));
        ExcelTemplateParam excelTemplateParamGet = super.findOneEntity(excelTemplateParamCriteria, new ArrayList<>());
        if (ESObjectUtils.isNotNull(excelTemplateParamGet) &&
                !ESStringUtils.equals(excelTemplateParam.getName(), excelTemplateParamGet.getName())) {
            throw new BusinessException("模板参数已存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveExcelTemplateParamRequestDTO, excelTemplateParam);
        return super.save(excelTemplateParam);
    }

    @Override
    public ExcelTemplateParamResponseDTO findByTemplateIdAndName(String templateId, String name) {
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
        excelTemplateParamCriteria.add(Restrictions.eq("templateId", templateId));
        excelTemplateParamCriteria.add(Restrictions.eq("name", name));
        return super.findOne(excelTemplateParamCriteria, new ArrayList<>());
    }

    @Override
    public List<ExcelTemplateParamResponseDTO> list(ListExcelTemplateParamRequestDTO listExcelTemplateParamRequestDTO) {
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
        excelTemplateParamCriteria.add(Restrictions.eq("templateId", listExcelTemplateParamRequestDTO.getTemplateId()));
        excelTemplateParamCriteria.add(Restrictions.eq("beanId", listExcelTemplateParamRequestDTO.getBeanId()));
        excelTemplateParamCriteria.add(Restrictions.eq("name", listExcelTemplateParamRequestDTO.getName()));
        return super.findAll(excelTemplateParamCriteria, listExcelTemplateParamRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<ExcelTemplateParamResponseDTO> page(PageExcelTemplateParamRequestDTO pageExcelTemplateParamRequestDTO) {
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
        excelTemplateParamCriteria.add(Restrictions.eq("templateId", pageExcelTemplateParamRequestDTO.getTemplateId()));
        excelTemplateParamCriteria.add(Restrictions.eq("beanId", pageExcelTemplateParamRequestDTO.getBeanId()));
        excelTemplateParamCriteria.add(Restrictions.eq("name", pageExcelTemplateParamRequestDTO.getName()));
        return super.findAll(excelTemplateParamCriteria, pageExcelTemplateParamRequestDTO.getPageRequestData());
    }

    @Override
    public ExcelTemplateParamResponseDTO generate(ExcelTemplateParam excelTemplateParam) {
        ExcelTemplateParamResponseDTO excelTemplateParamResponseDTO = new ExcelTemplateParamResponseDTO();
        ESBeanUtils.copyProperties(excelTemplateParam, excelTemplateParamResponseDTO);
        ExcelBeanPropertyResponseDTO excelBeanPropertyResponseDTO = excelBeanPropertyService.findByBeanIdAndName(excelTemplateParam.getBeanId(), excelTemplateParam.getName());
        excelTemplateParamResponseDTO.setType(ESObjectUtils.isNotNull(excelBeanPropertyResponseDTO) ? excelBeanPropertyResponseDTO.getType() : null);
        excelTemplateParamResponseDTO.setTitle(ESObjectUtils.isNotNull(excelBeanPropertyResponseDTO) ? excelBeanPropertyResponseDTO.getTitle() : null);
        return excelTemplateParamResponseDTO;
    }
}