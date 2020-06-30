package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.UserRole;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 服务操作类
 *
 * @author chenxun 2020-06-28 21:34:33
 **/
@Service
public class UserRoleManager extends BaseStringManager<UserRole> {

    public UserRole findByUserIdAndRoleId(String userId, String roleId) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("userId", userId));
        userRoleCriteria.add(Restrictions.eq("roleId", roleId));
        List<UserRole> userRoleList = super.findAll(userRoleCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        return userRoleList.get(0);
    }

    public void deleteAllByUserId(String userId) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("userId", userId));
        List<UserRole> userRoleList = super.findAll(userRoleCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(userRoleList)) {
            for (UserRole userRole : userRoleList) {
                super.deleteById(userRole.getId());
            }
        }
    }

    public void deleteAllByRoleId(String roleId) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("roleId", roleId));
        List<UserRole> userRoleList = super.findAll(userRoleCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(userRoleList)) {
            for (UserRole userRole : userRoleList) {
                super.deleteById(userRole.getId());
            }
        }
    }
}