package com.wondernect.stars.office.excel.param.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.PageExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.SaveExcelTemplateParamRequestDTO;

import java.util.List;

/**
 * excel导入导出模板配置服务接口类
 *
 * @author chenxun 2020-11-17 16:20:19
 **/
public interface ExcelTemplateParamInterface {

    /**
     * 创建
     **/
    ExcelTemplateParamResponseDTO create(SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO);

    /**
     * 更新
     **/
    ExcelTemplateParamResponseDTO update(String id, SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    ExcelTemplateParamResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<ExcelTemplateParamResponseDTO> list(ListExcelTemplateParamRequestDTO listExcelTemplateParamRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<ExcelTemplateParamResponseDTO> page(PageExcelTemplateParamRequestDTO pageExcelTemplateParamRequestDTO);
}