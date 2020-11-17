package com.wondernect.stars.office.feign.excel.template;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.template.ExcelTemplateResponseDTO;
import com.wondernect.stars.office.excel.dto.template.ListExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.PageExcelTemplateRequestDTO;
import com.wondernect.stars.office.excel.dto.template.SaveExcelTemplateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DepartmentFeignService
 * Author: chenxun
 * Date: 2019/8/1 19:37
 * Description: 部门服务
 */
@Service
public class ExcelTemplateServerService {

    @Autowired
    private ExcelTemplateFeignClient excelTemplateFeignClient;

    public ExcelTemplateResponseDTO create(SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO) {
        BusinessData<ExcelTemplateResponseDTO> businessData = excelTemplateFeignClient.create(saveExcelTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public ExcelTemplateResponseDTO update(String id, SaveExcelTemplateRequestDTO saveExcelTemplateRequestDTO) {
        BusinessData<ExcelTemplateResponseDTO> businessData = excelTemplateFeignClient.update(id, saveExcelTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = excelTemplateFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public ExcelTemplateResponseDTO detail(String id) {
        BusinessData<ExcelTemplateResponseDTO> businessData = excelTemplateFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<ExcelTemplateResponseDTO> list(ListExcelTemplateRequestDTO listExcelTemplateRequestDTO) {
        BusinessData<List<ExcelTemplateResponseDTO>> businessData = excelTemplateFeignClient.list(listExcelTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<ExcelTemplateResponseDTO> page(PageExcelTemplateRequestDTO pageExcelTemplateRequestDTO) {
        BusinessData<PageResponseData<ExcelTemplateResponseDTO>> businessData = excelTemplateFeignClient.page(pageExcelTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
