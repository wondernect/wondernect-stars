package com.wondernect.stars.rbac.service.role;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitRoleService
 * Author: chenxun
 * Date: 2020-06-23 17:37
 * Description:
 */
public interface RoleInterface {

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
    void deleteById(String id);

    /**
     * 获取角色详情
     */
    RoleResponseDTO findByCode(String code);

    /**
     * 获取角色详情
     */
    RoleResponseDTO findById(String id);

    /**
     * 角色列表
     */
    List<RoleResponseDTO> list(ListRoleRequestDTO listRoleRequestDTO);

    /**
     * 角色分页
     */
    PageResponseData<RoleResponseDTO> page(PageRoleRequestDTO pageRoleRequestDTO);
}
