package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.repository.BaseStringRepository;
import com.wondernect.stars.user.model.User;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
public interface UserRepository extends BaseStringRepository<User> {

    User findByMobile(String mobile);

    User findByEmail(String email);
}
