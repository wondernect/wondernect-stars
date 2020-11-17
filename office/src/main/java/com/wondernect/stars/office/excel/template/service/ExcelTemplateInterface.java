package com.wondernect.stars.office.excel.template.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.template.ExcelTemplateResponseDTO;
import com.wondernect.stars.office.excel.dto.template.ListExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.PageExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.SaveExcelTemplateRequestDTO;

import java.util.List;

/**
 * excel导入导出模板服务接口类
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
public interface ExcelTemplateInterface {

    /**
     * 创建
     **/
    ExcelTemplateResponseDTO create(SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO);

    /**
     * 更新
     **/
    ExcelTemplateResponseDTO update(String id, SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    ExcelTemplateResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<ExcelTemplateResponseDTO> list(ListExcelTemplateRequestDTO listExcelTemplateRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<ExcelTemplateResponseDTO> page(PageExcelTemplateRequestDTO pageExcelTemplateRequestDTO);
}