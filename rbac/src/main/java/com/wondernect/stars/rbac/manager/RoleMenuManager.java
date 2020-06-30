package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.RoleMenu;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuManager
 * Author: chenxun
 * Date: 2020-06-23 10:36
 * Description:
 */
@Service
public class RoleMenuManager extends BaseStringManager<RoleMenu> {

    public RoleMenu findByRoleIdAndMenuId(String roleId, String menuId) {
        Criteria<RoleMenu> roleMenuCriteria = new Criteria<>();
        roleMenuCriteria.add(Restrictions.eq("roleId", roleId));
        roleMenuCriteria.add(Restrictions.eq("menuId", menuId));
        List<RoleMenu> roleMenuList = super.findAll(roleMenuCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return null;
        }
        return roleMenuList.get(0);
    }

    @Transactional
    public void deleteAllByRoleId(String roleId) {
        Criteria<RoleMenu> rolePrivilegeCriteria = new Criteria<>();
        rolePrivilegeCriteria.add(Restrictions.eq("roleId", roleId));
        List<RoleMenu> roleMenuList = super.findAll(rolePrivilegeCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            for (RoleMenu roleMenu : roleMenuList) {
                super.deleteById(roleMenu.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByMenuId(String menuId) {
        Criteria<RoleMenu> rolePrivilegeCriteria = new Criteria<>();
        rolePrivilegeCriteria.add(Restrictions.eq("menuId", menuId));
        List<RoleMenu> roleMenuList = super.findAll(rolePrivilegeCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            for (RoleMenu roleMenu : roleMenuList) {
                super.deleteById(roleMenu.getId());
            }
        }
    }
}
