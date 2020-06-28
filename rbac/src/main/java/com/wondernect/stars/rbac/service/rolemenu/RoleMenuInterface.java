package com.wondernect.stars.rbac.service.rolemenu;

import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitRoleMenuService
 * Author: chenxun
 * Date: 2020-06-23 17:47
 * Description:
 */
public interface RoleMenuInterface {

    /**
     * 添加角色菜单
     */
    void add(RoleMenuRequestDTO roleMenuRequestDTO);

    /**
     * 编辑角色菜单
     */
    void edit(RoleMenuRequestDTO roleMenuRequestDTO);

    /**
     * 删除角色菜单
     */
    void delete(RoleMenuRequestDTO roleMenuRequestDTO);

    /**
     * 角色菜单树
     */
    RoleMenuTreeResponseDTO tree(String roleCode, String menuCode);
}
