package com.wondernect.stars.rbac.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeService
 * Author: chenxun
 * Date: 2020-06-23 17:17
 * Description:
 */
public interface InitRoleTypeService {

    /**
     * 创建角色类型
     */
    RoleTypeResponseDTO create(SaveRoleTypeRequestDTO saveRoleTypeRequestDTO);

    /**
     * 更新角色类型
     */
    RoleTypeResponseDTO update(String id, SaveRoleTypeRequestDTO saveRoleTypeRequestDTO);

    /**
     * 删除角色类型
     */
    void deleteById(String id);

    /**
     * 获取角色类型
     */
    RoleTypeResponseDTO findByCode(String code);

    /**
     * 获取角色类型
     */
    RoleTypeResponseDTO findById(String id);

    /**
     * 角色类型列表
     */
    List<RoleTypeResponseDTO> list(ListRoleTypeRequestDTO listRoleTypeRequestDTO);

    /**
     * 角色类型分页
     */
    PageResponseData<RoleTypeResponseDTO> page(PageRoleTypeRequestDTO pageRoleTypeRequestDTO);
}
