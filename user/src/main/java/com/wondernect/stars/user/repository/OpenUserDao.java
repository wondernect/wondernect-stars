package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.stars.user.model.OpenUser;
import com.wondernect.stars.user.model.em.AppType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class OpenUserDao extends BaseStringDao<OpenUser> {

    @Autowired
    private OpenUserRepository openUserRepository;

    public OpenUser findByUserId(String userId) {
        OpenUser openUser;
        try {
            openUser = openUserRepository.findByUserId(userId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return openUser;
    }

    public OpenUser findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        OpenUser openUser;
        try {
            openUser = openUserRepository.findByAppTypeAndAppUserId(appType, appUserId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return openUser;
    }
}
