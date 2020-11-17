package com.wondernect.stars.office.feign.excel.property;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.property.ExcelBeanPropertyResponseDTO;
import com.wondernect.stars.office.excel.dto.property.ListExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.PageExcelBeanPropertyRequestDTO;
import com.wondernect.stars.office.excel.dto.property.SaveExcelBeanPropertyRequestDTO;
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
public class ExcelBeanPropertyServerService {

    @Autowired
    private ExcelBeanPropertyFeignClient excelBeanPropertyFeignClient;

    public ExcelBeanPropertyResponseDTO create(SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO) {
        BusinessData<ExcelBeanPropertyResponseDTO> businessData = excelBeanPropertyFeignClient.create(saveExcelBeanPropertyRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public ExcelBeanPropertyResponseDTO update(String id, SaveExcelBeanPropertyRequestDTO saveExcelBeanPropertyRequestDTO) {
        BusinessData<ExcelBeanPropertyResponseDTO> businessData = excelBeanPropertyFeignClient.update(id, saveExcelBeanPropertyRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = excelBeanPropertyFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public ExcelBeanPropertyResponseDTO detail(String id) {
        BusinessData<ExcelBeanPropertyResponseDTO> businessData = excelBeanPropertyFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<ExcelBeanPropertyResponseDTO> list(ListExcelBeanPropertyRequestDTO listExcelBeanPropertyRequestDTO) {
        BusinessData<List<ExcelBeanPropertyResponseDTO>> businessData = excelBeanPropertyFeignClient.list(listExcelBeanPropertyRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<ExcelBeanPropertyResponseDTO> page(PageExcelBeanPropertyRequestDTO pageExcelBeanPropertyRequestDTO) {
        BusinessData<PageResponseData<ExcelBeanPropertyResponseDTO>> businessData = excelBeanPropertyFeignClient.page(pageExcelBeanPropertyRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
