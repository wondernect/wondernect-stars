package com.wondernect.stars.office.feign.excel.param;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.param.*;
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
public class ExcelTemplateParamServerService {

    @Autowired
    private ExcelTemplateParamFeignClient excelTemplateParamFeignClient;

    public void batchAdd(BatchAddExcelTemplateParamRequestDTO batchAddExcelTemplateParamRequestDTO) {
        BusinessData businessData = excelTemplateParamFeignClient.batchAdd(batchAddExcelTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public ExcelTemplateParamResponseDTO create(SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO) {
        BusinessData<ExcelTemplateParamResponseDTO> businessData = excelTemplateParamFeignClient.create(saveExcelTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public ExcelTemplateParamResponseDTO update(String id, SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO) {
        BusinessData<ExcelTemplateParamResponseDTO> businessData = excelTemplateParamFeignClient.update(id, saveExcelTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = excelTemplateParamFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public ExcelTemplateParamResponseDTO detail(String id) {
        BusinessData<ExcelTemplateParamResponseDTO> businessData = excelTemplateParamFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<ExcelTemplateParamResponseDTO> list(ListExcelTemplateParamRequestDTO listExcelTemplateParamRequestDTO) {
        BusinessData<List<ExcelTemplateParamResponseDTO>> businessData = excelTemplateParamFeignClient.list(listExcelTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<ExcelTemplateParamResponseDTO> page(PageExcelTemplateParamRequestDTO pageExcelTemplateParamRequestDTO) {
        BusinessData<PageResponseData<ExcelTemplateParamResponseDTO>> businessData = excelTemplateParamFeignClient.page(pageExcelTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
