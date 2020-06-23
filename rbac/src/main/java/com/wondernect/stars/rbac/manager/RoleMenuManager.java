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

    public RoleMenu findByRoleCodeAndMenuCode(String roleCode, String menuCode) {
        Criteria<RoleMenu> roleMenuCriteria = new Criteria<>();
        roleMenuCriteria.add(Restrictions.eq("roleCode", roleCode));
        roleMenuCriteria.add(Restrictions.eq("menuCode", menuCode));
        List<RoleMenu> roleMenuList = super.findAll(roleMenuCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(roleMenuList)) {
            return null;
        }
        return roleMenuList.get(0);
    }

    @Transactional
    public void deleteAllByRoleCode(String roleCode) {
        Criteria<RoleMenu> rolePrivilegeCriteria = new Criteria<>();
        rolePrivilegeCriteria.add(Restrictions.eq("roleCode", roleCode));
        List<RoleMenu> roleMenuList = super.findAll(rolePrivilegeCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            for (RoleMenu roleMenu : roleMenuList) {
                super.deleteById(roleMenu.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByMenuCode(String menuCode) {
        Criteria<RoleMenu> rolePrivilegeCriteria = new Criteria<>();
        rolePrivilegeCriteria.add(Restrictions.eq("menuCode", menuCode));
        List<RoleMenu> roleMenuList = super.findAll(rolePrivilegeCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            for (RoleMenu roleMenu : roleMenuList) {
                super.deleteById(roleMenu.getId());
            }
        }
    }
}
