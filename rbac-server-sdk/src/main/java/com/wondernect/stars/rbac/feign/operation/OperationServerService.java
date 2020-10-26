package com.wondernect.stars.rbac.feign.operation;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.ListMenuRequestDTO;
import com.wondernect.stars.rbac.dto.menu.MenuResponseDTO;
import com.wondernect.stars.rbac.dto.menu.PageMenuRequestDTO;
import com.wondernect.stars.rbac.dto.menu.SaveMenuRequestDTO;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.SaveOperationRequestDTO;
import com.wondernect.stars.rbac.feign.menu.MenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class OperationServerService {

    @Autowired
    private OperationFeignClient operationFeignClient;

    public OperationResponseDTO create(SaveOperationRequestDTO saveOperationRequestDTO){
        BusinessData<OperationResponseDTO> businessData = operationFeignClient.create(saveOperationRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public OperationResponseDTO update(String id,SaveOperationRequestDTO saveOperationRequestDTO){
        BusinessData<OperationResponseDTO> businessData = operationFeignClient.update(id, saveOperationRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = operationFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public OperationResponseDTO get(String id){
        BusinessData<OperationResponseDTO> businessData = operationFeignClient.get(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<OperationResponseDTO> list(ListOperationRequestDTO listOperationRequestDTO){
        BusinessData<List<OperationResponseDTO>> businessData = operationFeignClient.list(listOperationRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<OperationResponseDTO> page(PageOperationRequestDTO pageOperationRequestDTO){
        BusinessData<PageResponseData<OperationResponseDTO>> businessData = operationFeignClient.page(pageOperationRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
