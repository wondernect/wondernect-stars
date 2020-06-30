package com.wondernect.stars.rbac.service.menu;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitMenuService
 * Author: chenxun
 * Date: 2020-06-23 17:47
 * Description:
 */
public interface MenuInterface {

    /**
     * 创建菜单
     */
    MenuResponseDTO create(SaveMenuRequestDTO saveMenuRequestDTO);

    /**
     * 更新菜单
     */
    MenuResponseDTO update(String id, SaveMenuRequestDTO saveMenuRequestDTO);

    /**
     * 删除菜单
     */
    void deleteById(String id);

    /**
     * 获取菜单详情
     */
    MenuResponseDTO findById(String id);

    /**
     * 获取子菜单计数
     */
    long countByParentMenuId(String parentMenuId);

    /**
     * 菜单列表
     */
    List<MenuResponseDTO> list(ListMenuRequestDTO listMenuRequestDTO);

    /**
     * 菜单分页
     */
    PageResponseData<MenuResponseDTO> page(PageMenuRequestDTO pageMenuRequestDTO);

    /**
     * 菜单树形结构
     */
    MenuTreeResponseDTO tree(String id);
}
