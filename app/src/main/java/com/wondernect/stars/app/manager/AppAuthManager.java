package com.wondernect.stars.app.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.app.cache.AppAuthCache;
import com.wondernect.stars.app.model.AppAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 应用访问认证服务操作类
 *
 * @author chenxun 2020-12-29 16:28:22
 **/
@Service
public class AppAuthManager extends BaseStringManager<AppAuth> {

    @Autowired
    private AppAuthCache appAuthCache;

    @Override
    public AppAuth save(AppAuth appAuth) {
        AppAuth appAuthSave = super.save(appAuth);
        appAuthCache.save(appAuthSave);
        return appAuthSave;
    }

    @Override
    public void deleteById(String id) {
        AppAuth appAuth = super.findById(id);
        if (ESObjectUtils.isNotNull(appAuth)) {
            appAuthCache.remove(appAuth.getAppId(), appAuth.getUserId());
            super.deleteById(id);
        }
    }

    public AppAuth findByAppIdAndUserId(String appId, String userId) {
        AppAuth appAuth = appAuthCache.get(appId, userId);
        if (ESObjectUtils.isNull(appAuth)) {
            Criteria<AppAuth> appAuthCriteria = new Criteria<>();
            appAuthCriteria.add(Restrictions.eq("appId", appId));
            appAuthCriteria.add(Restrictions.eq("userId", userId));
            appAuth = super.findOne(appAuthCriteria, new ArrayList<>());
            if (ESObjectUtils.isNotNull(appAuth)) {
                appAuthCache.save(appAuth);
            }
        }
        return appAuth;
    }
}