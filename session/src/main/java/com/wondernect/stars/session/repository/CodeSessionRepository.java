package com.wondernect.stars.session.repository;

import com.wondernect.elements.rdb.base.repository.BaseCodeRepository;
import com.wondernect.stars.session.model.CodeSession;

/**
 * Created on 2018/4/25.
 * wondernect.com
 * @author sunbeam
 */
public interface CodeSessionRepository extends BaseCodeRepository<CodeSession> {

    CodeSession findTopByCreateUserOrderByCreateTimeDesc(String createUser);
}
