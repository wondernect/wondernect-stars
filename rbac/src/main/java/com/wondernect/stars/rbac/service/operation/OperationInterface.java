package com.wondernect.stars.rbac.service.operation;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.SaveOperationRequestDTO;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitOperationService
 * Author: chenxun
 * Date: 2020-06-23 17:47
 * Description:
 */
public interface OperationInterface {

    /**
     * 创建操作
     */
    OperationResponseDTO create(SaveOperationRequestDTO saveOperationRequestDTO);

    /**
     * 更新操作
     */
    OperationResponseDTO update(String id, SaveOperationRequestDTO saveOperationRequestDTO);

    /**
     * 删除操作
     */
    void deleteById(String id);

    /**
     * 获取操作
     */
    OperationResponseDTO findById(String id);

    /**
     * 操作列表
     */
    List<OperationResponseDTO> list(ListOperationRequestDTO listOperationRequestDTO);

    /**
     * 操作分页
     */
    PageResponseData<OperationResponseDTO> page(PageOperationRequestDTO pageOperationRequestDTO);
}
