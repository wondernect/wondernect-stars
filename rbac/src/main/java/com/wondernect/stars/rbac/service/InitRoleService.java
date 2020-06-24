package com.wondernect.stars.rbac.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.model.Role;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitRoleService
 * Author: chenxun
 * Date: 2020-06-23 17:37
 * Description:
 */
public interface InitRoleService {

    /**
     * 创建角色
     */
    RoleResponseDTO create(SaveRoleRequestDTO saveRoleRequestDTO);

    /**
     * 更新角色
     */
    RoleResponseDTO update(String id, SaveRoleRequestDTO saveRoleRequestDTO);

    /**
     * 删除角色
     */
    void delete(String id);

    /**
     * 获取角色详情
     */
    RoleResponseDTO getByCode(String code);

    /**
     * 获取角色详情
     */
    RoleResponseDTO getById(String id);

    /**
     * 角色列表
     */
    List<RoleResponseDTO> list(ListRoleRequestDTO listRoleRequestDTO);

    /**
     * 角色分页
     */
    PageResponseData<RoleResponseDTO> page(PageRoleRequestDTO pageRoleRequestDTO);

    /**
     * 构造角色响应
     */
    RoleResponseDTO generate(Role role);
}