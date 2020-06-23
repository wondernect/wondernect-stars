package com.wondernect.stars.rbac.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.model.Menu;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitMenuService
 * Author: chenxun
 * Date: 2020-06-23 17:47
 * Description:
 */
public interface InitMenuService {

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
    void delete(String id);

    /**
     * 获取菜单详情
     */
    MenuResponseDTO getByCode(String code);

    /**
     * 获取菜单详情
     */
    MenuResponseDTO getById(String id);

    /**
     * 获取子菜单计数
     */
    long countByParentMenuCode(String parentMenuCode);

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
    MenuTreeResponseDTO tree(String code);

    /**
     * 构造菜单响应结果
     */
    MenuResponseDTO generate(Menu menu);
}
