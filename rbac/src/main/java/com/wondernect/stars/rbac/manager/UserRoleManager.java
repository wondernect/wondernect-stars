package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.UserRole;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 服务操作类
 *
 * @author chenxun 2020-06-28 21:34:33
 **/
@Service
public class UserRoleManager extends BaseStringManager<UserRole> {

    public UserRole findByUserIdAndRole(String userId, String role) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("userId", userId));
        userRoleCriteria.add(Restrictions.eq("role", role));
        List<UserRole> userRoleList = super.findAll(userRoleCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(userRoleList)) {
            return null;
        }
        return userRoleList.get(0);
    }
}