package com.wondernect.stars.user.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.user.cache.OpenUserCache;
import com.wondernect.stars.user.model.OpenUser;
import com.wondernect.stars.user.model.em.AppType;
import com.wondernect.stars.user.repository.OpenUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class OpenUserManager extends BaseStringManager<OpenUser> {

    @Autowired
    private OpenUserDao openUserDao;

    @Autowired
    private OpenUserCache openUserCache;

    public OpenUser save(OpenUser openUser) {
        OpenUser openUserSave = openUserDao.save(openUser);
        openUserCache.save(openUserSave);
        return openUserSave;
    }

    public void deleteById(String openUserId) {
        OpenUser openUser = findById(openUserId);
        if (ESObjectUtils.isNotNull(openUser)) {
            openUserCache.remove(openUser.getUserId());
            openUserDao.deleteById(openUserId);
        }
    }

    public void deleteByUserId(String userId) {
        OpenUser openUser = findByUserId(userId);
        if (ESObjectUtils.isNotNull(openUser)) {
            openUserCache.remove(userId);
            openUserDao.deleteById(openUser.getId());
        }
    }

    public OpenUser findByUserId(String userId) {
        OpenUser openUser = openUserCache.get(userId);
        if (ESObjectUtils.isNull(openUser)) {
            openUser = openUserDao.findByUserId(userId);
            if (ESObjectUtils.isNotNull(openUser)) {
                openUserCache.save(openUser);
            }
        }
        return openUser;
    }

    public OpenUser findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        return openUserDao.findByAppTypeAndAppUserId(appType, appUserId);
    }
}
