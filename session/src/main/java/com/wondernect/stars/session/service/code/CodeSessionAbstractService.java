package com.wondernect.stars.session.service.code;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.rdb.base.service.BaseCodeService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.config.SessionConfigProperties;
import com.wondernect.stars.session.dto.code.*;
import com.wondernect.stars.session.manager.CodeSessionManager;
import com.wondernect.stars.session.model.CodeSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AbstractCodeSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
public abstract class CodeSessionAbstractService extends BaseCodeService<CodeResponseDTO, CodeSession> implements CodeSessionInterface {

    @Autowired
    private SessionConfigProperties sessionConfigProperties;

    @Autowired
    private CodeSessionManager codeSessionManager;

    public CodeResponseDTO requestCodeSession(CodeRequestDTO codeRequestDTO) {
        Long expires = codeRequestDTO.getExpires();
        if (ESObjectUtils.isNull(expires) || expires <= 0L) {
            expires = sessionConfigProperties.getCodeExpires();
        }
        return super.save(
                new CodeSession(
                        codeRequestDTO.getUserId(),
                        codeRequestDTO.getDescription(),
                        expires,
                        codeRequestDTO.getIp(),
                        codeRequestDTO.getDevicePlatform(),
                        codeRequestDTO.getDeviceDescription()
                )
        );
    }

    public void deleteCacheByCode(String code) {
        CodeSession codeSession = codeSessionManager.findCacheByCode(code);
        if (ESObjectUtils.isNotNull(codeSession)) {
            codeSessionManager.deleteCacheByCode(code);
        }
    }

    public CodeResponseDTO findCacheByCode(String code) {
        CodeSession codeSession = codeSessionManager.findCacheByCode(code);
        if (ESObjectUtils.isNull(codeSession)) {
            return null;
        }
        return generate(codeSession);
    }

    public CodeResponseDTO refreshCodeSession(CodeRefreshRequestDTO codeRefreshRequestDTO) {
        CodeSession codeSession = super.findEntityByCode(codeRefreshRequestDTO.getCode());
        if (ESObjectUtils.isNull(codeSession)) {
            throw new SessionException(SessionErrorEnum.CODE_SESSION_NOT_FOUND);
        } else {
            Long expires = codeRefreshRequestDTO.getExpires();
            if (ESObjectUtils.isNull(expires) || expires <= 0L) {
                expires = sessionConfigProperties.getCodeExpires();
            }
            return super.save(
                    new CodeSession(
                            codeRefreshRequestDTO.getUserId(),
                            codeRefreshRequestDTO.getDescription(),
                            expires,
                            codeRefreshRequestDTO.getIp(),
                            codeRefreshRequestDTO.getDevicePlatform(),
                            codeRefreshRequestDTO.getDeviceDescription()
                    )
            );
        }
    }

    public CodeResponseDTO authCodeSession(CodeAuthRequestDTO codeAuthRequestDTO) {
        CodeSession codeSession = codeSessionManager.findCacheByCode(codeAuthRequestDTO.getCode());
        if (ESObjectUtils.isNull(codeSession)) {
            throw new SessionException(SessionErrorEnum.CODE_SESSION_NOT_FOUND);
        }
        return generate(codeSession);
    }

    public List<CodeResponseDTO> list(ListCodeRequestDTO listCodeRequestDTO) {
        Criteria<CodeSession> codeSessionCriteria = new Criteria<>(LogicalOperator.OR);
        codeSessionCriteria.add(Restrictions.eq("userId", listCodeRequestDTO.getValue()));
        return super.findAll(codeSessionCriteria, listCodeRequestDTO.getSortDataList());
    }

    public PageResponseData<CodeResponseDTO> page(PageCodeRequestDTO pageCodeRequestDTO) {
        Criteria<CodeSession> codeSessionCriteria = new Criteria<>(LogicalOperator.OR);
        codeSessionCriteria.add(Restrictions.eq("userId", pageCodeRequestDTO.getValue()));
        return super.findAll(codeSessionCriteria, pageCodeRequestDTO.getPageRequestData());
    }

    public CodeResponseDTO generate(CodeSession codeSession) {
        CodeResponseDTO codeResponseDTO = new CodeResponseDTO();
        ESBeanUtils.copyProperties(codeSession, codeResponseDTO);
        codeResponseDTO.setCode(codeSession.getCode());
        codeResponseDTO.setCreateTime(codeSession.getCreateTime());
        return codeResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return null;
    }
}
