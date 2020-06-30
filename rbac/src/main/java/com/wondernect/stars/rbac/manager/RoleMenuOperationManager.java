package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.RoleMenuOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuOperationManager
 * Author: chenxun
 * Date: 2020-06-23 10:37
 * Description:
 */
@Service
public class RoleMenuOperationManager extends BaseStringManager<RoleMenuOperation> {

    public RoleMenuOperation findByRoleIdAndMenuIdAndOperationId(String roleId, String menuId, String operationId) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("roleId", roleId));
        roleMenuOperationCriteria.add(Restrictions.eq("menuId", menuId));
        roleMenuOperationCriteria.add(Restrictions.eq("operationId", operationId));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(roleMenuOperationList)) {
            return null;
        }
        return roleMenuOperationList.get(0);
    }

    @Transactional
    public void deleteAllByRoleId(String roleId) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("roleId", roleId));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByMenuId(String menuId) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("menuId", menuId));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByOperationId(String operationId) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("operationId", operationId));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByRoleIdAndMenuId(String roleId, String menuId) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("roleId", roleId));
        roleMenuOperationCriteria.add(Restrictions.eq("menuId", menuId));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }
}
