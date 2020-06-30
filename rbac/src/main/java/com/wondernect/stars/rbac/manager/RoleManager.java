package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.rbac.model.Role;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleManager
 * Author: chenxun
 * Date: 2020-06-23 10:34
 * Description:
 */
@Service
public class RoleManager extends BaseStringManager<Role> {

    /**
     * 根据角色类型id删除所有对应角色
     */
    @Transactional
    public void deleteAllByRoleTypeId(String roleTypeId) {
        Criteria<Role> roleCriteria = new Criteria<>();
        roleCriteria.add(Restrictions.eq("roleTypeId", roleTypeId));
        List<Role> roleList = super.findAll(roleCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleList)) {
            for (Role role : roleList) {
                super.deleteById(role.getId());
            }
        }
    }
}
