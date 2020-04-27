package com.wondernect.stars.session.repository.crud;

import com.wondernect.elements.rdb.base.repository.BaseTokenRepository;
import com.wondernect.stars.session.model.TokenSession;

/**
 * Created on 2018/4/7.
 * wondernect.com
 * @author sunbeam
 */
public interface TokenSessionRepository extends BaseTokenRepository<TokenSession> {

    TokenSession findTopByCreateUserOrderByCreateTimeDesc(String createUser);
}
