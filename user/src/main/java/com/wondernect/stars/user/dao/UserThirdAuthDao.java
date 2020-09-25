package com.wondernect.stars.user.dao;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.elements.rdb.config.AppFilter;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.model.UserThirdAuth;
import com.wondernect.stars.user.repository.UserThirdAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class UserThirdAuthDao extends BaseStringDao<UserThirdAuth> {

    @Autowired
    private UserThirdAuthRepository userThirdAuthRepository;

    @AppFilter
    @Transactional
    public UserThirdAuth findByUserIdAndAppType(String userId, AppType appType) {
        UserThirdAuth userThirdAuth;
        try {
            userThirdAuth = userThirdAuthRepository.findByUserIdAndAppType(userId, appType);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return userThirdAuth;
    }

    @AppFilter
    @Transactional
    public UserThirdAuth findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        UserThirdAuth userThirdAuth;
        try {
            userThirdAuth = userThirdAuthRepository.findByAppTypeAndAppUserId(appType, appUserId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return userThirdAuth;
    }
}
