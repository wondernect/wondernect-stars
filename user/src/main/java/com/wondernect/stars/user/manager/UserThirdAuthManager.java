package com.wondernect.stars.user.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.user.dao.UserThirdAuthDao;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.model.UserThirdAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class UserThirdAuthManager extends BaseStringManager<UserThirdAuth> {

    @Autowired
    private UserThirdAuthDao userThirdAuthDao;

    public UserThirdAuth findByUserIdAndAppType(String userId, AppType appType) {
        return userThirdAuthDao.findByUserIdAndAppType(userId, appType);
    }

    public UserThirdAuth findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        return userThirdAuthDao.findByAppTypeAndAppUserId(appType, appUserId);
    }
}
