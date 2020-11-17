package com.wondernect.stars.office.excel.bean.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.bean.ExcelBeanResponseDTO;
import com.wondernect.stars.office.excel.dto.bean.ListExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.PageExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.SaveExcelBeanRequestDTO;

import java.util.List;

/**
 * excel导入导出实体类服务接口类
 *
 * @author chenxun 2020-11-17 16:18:30
 **/
public interface ExcelBeanInterface {

    /**
     * 创建
     **/
    ExcelBeanResponseDTO create(SaveExcelBeanRequestDTO saveExcelBeanRequestDTO);

    /**
     * 更新
     **/
    ExcelBeanResponseDTO update(String id, SaveExcelBeanRequestDTO saveExcelBeanRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    ExcelBeanResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<ExcelBeanResponseDTO> list(ListExcelBeanRequestDTO listExcelBeanRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<ExcelBeanResponseDTO> page(PageExcelBeanRequestDTO pageExcelBeanRequestDTO);
}