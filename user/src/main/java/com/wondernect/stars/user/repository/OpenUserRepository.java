package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.repository.BaseStringRepository;
import com.wondernect.stars.user.model.OpenUser;
import com.wondernect.stars.user.model.em.AppType;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public interface OpenUserRepository extends BaseStringRepository<OpenUser> {

    OpenUser findByUserId(String userId);

    OpenUser findByAppTypeAndAppUserId(AppType appType, String appUserId);
}
