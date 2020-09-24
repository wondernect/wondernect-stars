package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.repository.BaseStringRepository;
import com.wondernect.stars.user.model.User;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
public interface UserRepository extends BaseStringRepository<User> {

    User findByMobileAndCreateApp(String mobile, String appId);

    User findByEmailAndCreateApp(String email, String appId);

    User findByUsernameAndCreateApp(String username, String appId);
}
