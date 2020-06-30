package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.Menu;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: MenuManager
 * Author: chenxun
 * Date: 2020-06-23 10:34
 * Description:
 */
@Service
public class MenuManager extends BaseStringManager<Menu> {

    /**
     * 根据菜单代码获取菜单
     */
    public Menu findByCode(String menuCode) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("code", menuCode));
        List<Menu> menuList = super.findAll(menuCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(menuList)) {
            return null;
        }
        return menuList.get(0);
    }
}
