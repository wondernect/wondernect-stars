package com.wondernect.stars.office.feign.excel.bean;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.bean.ExcelBeanResponseDTO;
import com.wondernect.stars.office.excel.dto.bean.ListExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.PageExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.SaveExcelBeanRequestDTO;
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
public class ExcelBeanServerService {

    @Autowired
    private ExcelBeanFeignClient excelBeanFeignClient;

    public ExcelBeanResponseDTO create(SaveExcelBeanRequestDTO saveExcelBeanRequestDTO) {
        BusinessData<ExcelBeanResponseDTO> businessData = excelBeanFeignClient.create(saveExcelBeanRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public ExcelBeanResponseDTO update(String id, SaveExcelBeanRequestDTO saveExcelBeanRequestDTO) {
        BusinessData<ExcelBeanResponseDTO> businessData = excelBeanFeignClient.update(id, saveExcelBeanRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = excelBeanFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public ExcelBeanResponseDTO detail(String id) {
        BusinessData<ExcelBeanResponseDTO> businessData = excelBeanFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<ExcelBeanResponseDTO> list(ListExcelBeanRequestDTO listExcelBeanRequestDTO) {
        BusinessData<List<ExcelBeanResponseDTO>> businessData = excelBeanFeignClient.list(listExcelBeanRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<ExcelBeanResponseDTO> page(PageExcelBeanRequestDTO pageExcelBeanRequestDTO) {
        BusinessData<PageResponseData<ExcelBeanResponseDTO>> businessData = excelBeanFeignClient.page(pageExcelBeanRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
