package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.RoleType;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeManager
 * Author: chenxun
 * Date: 2020-06-23 10:32
 * Description:
 */
@Service
public class RoleTypeManager extends BaseStringManager<RoleType> {

    /**
     * 根据角色类型代码获取角色类型
     */
    public RoleType findByCode(String code) {
        Criteria<RoleType> roleTypeCriteria = new Criteria<>();
        roleTypeCriteria.add(Restrictions.eq("code", code));
        List<RoleType> roleTypeList = super.findAll(roleTypeCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(roleTypeList)) {
            return null;
        }
        return roleTypeList.get(0);
    }
}
