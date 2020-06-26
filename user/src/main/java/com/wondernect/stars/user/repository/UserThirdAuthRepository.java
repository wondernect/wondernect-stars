package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.repository.BaseStringRepository;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.model.UserThirdAuth;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public interface UserThirdAuthRepository extends BaseStringRepository<UserThirdAuth> {

    UserThirdAuth findByUserIdAndAppType(String userId, AppType appType);

    UserThirdAuth findByAppTypeAndAppUserId(AppType appType, String appUserId);
}
