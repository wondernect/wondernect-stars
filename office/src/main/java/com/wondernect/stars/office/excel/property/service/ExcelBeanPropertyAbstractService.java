package com.wondernect.stars.office.excel.property.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.property.ExcelBeanPropertyResponseDTO;
import com.wondernect.stars.office.excel.dto.property.ListExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.PageExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.SaveExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.property.model.ExcelBeanProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * excel导入导出实体类属性服务抽象实现类
 *
 * @author chenxun 2020-11-17 16:19:04
 **/
@Service
public abstract class ExcelBeanPropertyAbstractService extends BaseStringService<ExcelBeanPropertyResponseDTO, ExcelBeanProperty> implements ExcelBeanPropertyInterface {

    @Transactional
    @Override
    public ExcelBeanPropertyResponseDTO create(SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO) {
//TODO:判断对象是否存在

        ExcelBeanProperty excelBeanProperty = new ExcelBeanProperty();
        ESBeanUtils.copyProperties(saveExcelBeanPropertyRequestDTO, excelBeanProperty);
        return super.save(excelBeanProperty);
    }

    @Transactional
    @Override
    public ExcelBeanPropertyResponseDTO update(String id, SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO) {
        ExcelBeanProperty excelBeanProperty = super.findEntityById(id);
        if (ESObjectUtils.isNull(excelBeanProperty)) {
            throw new BusinessException("excel导入导出实体类属性不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveExcelBeanPropertyRequestDTO, excelBeanProperty);
        return super.save(excelBeanProperty);
    }

    @Override
    public List<ExcelBeanPropertyResponseDTO> list(ListExcelBeanPropertyRequestDTO listExcelBeanPropertyRequestDTO) {
        Criteria<ExcelBeanProperty> excelBeanPropertyCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(excelBeanPropertyCriteria, listExcelBeanPropertyRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<ExcelBeanPropertyResponseDTO> page(PageExcelBeanPropertyRequestDTO pageExcelBeanPropertyRequestDTO) {
        Criteria<ExcelBeanProperty> excelBeanPropertyCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(excelBeanPropertyCriteria, pageExcelBeanPropertyRequestDTO.getPageRequestData());
    }

    @Override
    public ExcelBeanPropertyResponseDTO generate(ExcelBeanProperty excelBeanProperty) {
        ExcelBeanPropertyResponseDTO excelBeanPropertyResponseDTO = new ExcelBeanPropertyResponseDTO();
        ESBeanUtils.copyProperties(excelBeanProperty, excelBeanPropertyResponseDTO);
        return excelBeanPropertyResponseDTO;
    }

    @Override
    public ExcelBeanProperty generate(ExcelBeanPropertyResponseDTO excelBeanPropertyResponseDTO) {
        ExcelBeanProperty excelBeanProperty = new ExcelBeanProperty();
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(excelBeanPropertyResponseDTO, excelBeanProperty);
        return excelBeanProperty;
    }
}