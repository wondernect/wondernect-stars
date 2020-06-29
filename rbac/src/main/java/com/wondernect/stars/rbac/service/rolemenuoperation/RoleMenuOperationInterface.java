package com.wondernect.stars.rbac.service.rolemenuoperation;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitRoleMenuOperationService
 * Author: chenxun
 * Date: 2020-06-23 17:47
 * Description:
 */
public interface RoleMenuOperationInterface {

    /**
     * 添加角色菜单操作
     */
    void add(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO);

    /**
     * 编辑角色菜单操作
     */
    void edit(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO);

    /**
     * 删除角色菜单操作
     */
    void delete(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO);

    /**
     * 获取角色菜单对应操作
     */
    RoleMenuOperationResponseDTO findByRoleCodeAndMenuCodeAndOperationCode(String roleCode, String menuCode, String operationCode);

    /**
     * 角色菜单操作列表
     */
    List<RoleMenuOperationResponseDTO> list(ListRoleMenuOperationRequestDTO listRoleMenuOperationRequestDTO);

    /**
     * 角色菜单操作分页
     */
    PageResponseData<RoleMenuOperationResponseDTO> page(PageRoleMenuOperationRequestDTO pageRoleMenuOperationRequestDTO);
}
