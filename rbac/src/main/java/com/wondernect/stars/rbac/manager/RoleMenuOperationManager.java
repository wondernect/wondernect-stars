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

    public RoleMenuOperation findByRoleCodeAndMenuCodeAndOperationCode(String roleCode, String menuCode, String operationCode) {
        Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
        roleMenuOperationCriteria.add(Restrictions.eq("roleCode", roleCode));
        roleMenuOperationCriteria.add(Restrictions.eq("menuCode", menuCode));
        roleMenuOperationCriteria.add(Restrictions.eq("operationCode", operationCode));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(roleMenuOperationCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(roleMenuOperationList)) {
            return null;
        }
        return roleMenuOperationList.get(0);
    }

    @Transactional
    public void deleteAllByRoleCode(String roleCode) {
        Criteria<RoleMenuOperation> privilegeMenuCriteria = new Criteria<>();
        privilegeMenuCriteria.add(Restrictions.eq("roleCode", roleCode));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(privilegeMenuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByMenuCode(String menuCode) {
        Criteria<RoleMenuOperation> privilegeMenuCriteria = new Criteria<>();
        privilegeMenuCriteria.add(Restrictions.eq("menuCode", menuCode));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(privilegeMenuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByOperationCode(String operationCode) {
        Criteria<RoleMenuOperation> privilegeMenuCriteria = new Criteria<>();
        privilegeMenuCriteria.add(Restrictions.eq("operationCode", operationCode));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(privilegeMenuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }

    @Transactional
    public void deleteAllByRoleCodeAndMenuCode(String roleCode, String menuCode) {
        Criteria<RoleMenuOperation> privilegeMenuCriteria = new Criteria<>();
        privilegeMenuCriteria.add(Restrictions.eq("roleCode", roleCode));
        privilegeMenuCriteria.add(Restrictions.eq("menuCode", menuCode));
        List<RoleMenuOperation> roleMenuOperationList = super.findAll(privilegeMenuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
            for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                super.deleteById(roleMenuOperation.getId());
            }
        }
    }
}
