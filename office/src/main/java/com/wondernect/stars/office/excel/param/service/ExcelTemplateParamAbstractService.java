package com.wondernect.stars.office.excel.param.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.PageExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.SaveExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.param.model.ExcelTemplateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * excel导入导出模板配置服务抽象实现类
 *
 * @author chenxun 2020-11-17 16:20:19
 **/
@Service
public abstract class ExcelTemplateParamAbstractService extends BaseStringService<ExcelTemplateParamResponseDTO, ExcelTemplateParam> implements ExcelTemplateParamInterface {

    @Transactional
    @Override
    public ExcelTemplateParamResponseDTO create(SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO) {
//TODO:判断对象是否存在

        ExcelTemplateParam excelTemplateParam = new ExcelTemplateParam();
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
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveExcelTemplateParamRequestDTO, excelTemplateParam);
        return super.save(excelTemplateParam);
    }

    @Override
    public List<ExcelTemplateParamResponseDTO> list(ListExcelTemplateParamRequestDTO listExcelTemplateParamRequestDTO) {
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(excelTemplateParamCriteria, listExcelTemplateParamRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<ExcelTemplateParamResponseDTO> page(PageExcelTemplateParamRequestDTO pageExcelTemplateParamRequestDTO) {
        Criteria<ExcelTemplateParam> excelTemplateParamCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(excelTemplateParamCriteria, pageExcelTemplateParamRequestDTO.getPageRequestData());
    }

    @Override
    public ExcelTemplateParamResponseDTO generate(ExcelTemplateParam excelTemplateParam) {
        ExcelTemplateParamResponseDTO excelTemplateParamResponseDTO = new ExcelTemplateParamResponseDTO();
        ESBeanUtils.copyProperties(excelTemplateParam, excelTemplateParamResponseDTO);
        return excelTemplateParamResponseDTO;
    }

    @Override
    public ExcelTemplateParam generate(ExcelTemplateParamResponseDTO excelTemplateParamResponseDTO) {
        ExcelTemplateParam excelTemplateParam = new ExcelTemplateParam();
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(excelTemplateParamResponseDTO, excelTemplateParam);
        return excelTemplateParam;
    }
}