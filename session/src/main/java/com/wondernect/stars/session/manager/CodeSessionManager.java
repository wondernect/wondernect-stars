package com.wondernect.stars.session.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseCodeManager;
import com.wondernect.stars.session.cache.CodeSessionCache;
import com.wondernect.stars.session.model.CodeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class CodeSessionManager extends BaseCodeManager<CodeSession> {

    @Autowired
    private CodeSessionCache codeSessionCache;

    @Override
    public CodeSession save(CodeSession codeSession) {
        CodeSession codeSessionCreate = super.save(codeSession);
        codeSessionCache.save(codeSessionCreate);
        return codeSessionCreate;
    }

    @Override
    public void deleteByCode(String code) {
        codeSessionCache.remove(code);
        super.deleteByCode(code);
    }

    @Override
    public CodeSession findByCode(String code) {
        CodeSession codeSession = codeSessionCache.get(code);
        if (ESObjectUtils.isNull(codeSession)) {
            codeSession = super.findByCode(code);
        }
        return codeSession;
    }

    public void deleteCacheByCode(String code) {
        codeSessionCache.remove(code);
    }

    public CodeSession findCacheByCode(String code) {
        return codeSessionCache.get(code);
    }

    public void saveExpireCodeCache(CodeSession codeSession) {
        codeSessionCache.saveExpire(codeSession);
    }
}
