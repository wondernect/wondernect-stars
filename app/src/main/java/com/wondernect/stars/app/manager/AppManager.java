package com.wondernect.stars.app.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.app.cache.AppCache;
import com.wondernect.stars.app.dao.AppDao;
import com.wondernect.stars.app.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 应用服务操作类
 *
 * @author chenxun 2020-09-13 23:01:59
 **/
@Service
public class AppManager extends BaseStringManager<App> {

    @Autowired
    private AppCache appCache;

    @Override
    public App save(App app) {
        App appSave = super.save(app);
        appCache.save(appSave);
        return appSave;
    }

    @Override
    public void deleteById(String appId) {
        appCache.remove(appId);
        super.deleteById(appId);
    }

    @Override
    public App findById(String appId) {
        App app = appCache.get(appId);
        if (ESObjectUtils.isNull(app)) {
            app = super.findById(appId);
            if (ESObjectUtils.isNotNull(app)) {
                appCache.save(app);
            }
        }
        return app;
    }
}