package com.wondernect.stars.logger.feign.request;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.logger.dto.ListRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.PageRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.RequestLogResponseDTO;
import com.wondernect.stars.logger.dto.SaveRequestLogRequestDTO;
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
public class RequestLogServerService {

    @Autowired
    private RequestLogFeignClient requestLogFeignClient;

    public RequestLogResponseDTO create(SaveRequestLogRequestDTO saveRequestLogRequestDTO) {
        BusinessData<RequestLogResponseDTO> businessData = requestLogFeignClient.create(saveRequestLogRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public RequestLogResponseDTO update(String id, SaveRequestLogRequestDTO saveRequestLogRequestDTO) {
        BusinessData<RequestLogResponseDTO> businessData = requestLogFeignClient.update(id, saveRequestLogRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = requestLogFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public RequestLogResponseDTO detail(String id) {
        BusinessData<RequestLogResponseDTO> businessData = requestLogFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<RequestLogResponseDTO> list(ListRequestLogRequestDTO listRequestLogRequestDTO) {
        BusinessData<List<RequestLogResponseDTO>> businessData = requestLogFeignClient.list(listRequestLogRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<RequestLogResponseDTO> page(PageRequestLogRequestDTO pageRequestLogRequestDTO) {
        BusinessData<PageResponseData<RequestLogResponseDTO>> businessData = requestLogFeignClient.page(pageRequestLogRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
