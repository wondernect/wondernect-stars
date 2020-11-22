package com.wondernect.stars.office.excel.property.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.property.ExcelBeanPropertyResponseDTO;
import com.wondernect.stars.office.excel.dto.property.ListExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.PageExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.SaveExcelBeanPropertyRequestDTO;

import java.util.List;

/**
 * excel导入导出实体类属性服务接口类
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
public interface ExcelBeanPropertyInterface {

    /**
     * 创建
     **/
    ExcelBeanPropertyResponseDTO create(SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO);

    /**
     * 更新
     **/
    ExcelBeanPropertyResponseDTO update(String id, SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    ExcelBeanPropertyResponseDTO findById(String id);

    /**
     * 获取详细信息
     **/
    ExcelBeanPropertyResponseDTO findByBeanIdAndName(String beanId, String name);

    /**
     * 列表
     **/
    List<ExcelBeanPropertyResponseDTO> list(ListExcelBeanPropertyRequestDTO listExcelBeanPropertyRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<ExcelBeanPropertyResponseDTO> page(PageExcelBeanPropertyRequestDTO pageExcelBeanPropertyRequestDTO);
}