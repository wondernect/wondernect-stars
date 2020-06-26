package com.wondernect.stars.session.service.astract;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseTokenService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.dto.token.*;
import com.wondernect.stars.session.model.TokenSession;
import com.wondernect.stars.session.service.InitTokenSessionService;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultTokenSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
public abstract class AbstractTokenSessionService extends BaseTokenService<TokenResponseDTO, TokenSession> implements InitTokenSessionService {

    public TokenResponseDTO requestTokenSession(TokenRequestDTO tokenRequestDTO) {
        return super.save(
                new TokenSession(
                        tokenRequestDTO.getUserId(),
                        tokenRequestDTO.getDeviceIdentifier(),
                        tokenRequestDTO.getDescription(),
                        tokenRequestDTO.getIp(),
                        tokenRequestDTO.getDevicePlatform(),
                        tokenRequestDTO.getDeviceDescription()
                )
        );
    }

    public TokenResponseDTO refreshTokenSession(TokenRefreshRequestDTO tokenRefreshRequestDTO) {
        TokenSession tokenSession = super.findEntityByToken(tokenRefreshRequestDTO.getToken());
        if (ESObjectUtils.isNull(tokenSession)) {
            throw new SessionException(SessionErrorEnum.TOKEN_SESSION_NOT_FOUND);
        } else {
            return super.save(
                    new TokenSession(
                            tokenRefreshRequestDTO.getUserId(),
                            tokenRefreshRequestDTO.getDeviceIdentifier(),
                            tokenRefreshRequestDTO.getDescription(),
                            tokenRefreshRequestDTO.getIp(),
                            tokenRefreshRequestDTO.getDevicePlatform(),
                            tokenRefreshRequestDTO.getDeviceDescription()
                    )
            );
        }
    }

    public TokenResponseDTO authTokenSession(TokenAuthRequestDTO tokenAuthRequestDTO) {
        TokenSession tokenSession = super.findEntityByToken(tokenAuthRequestDTO.getToken());
        if (ESObjectUtils.isNull(tokenSession)) {
            throw new SessionException(SessionErrorEnum.TOKEN_SESSION_NOT_FOUND);
        }
        return generate(tokenSession);
    }

    public List<TokenResponseDTO> list(ListTokenRequestDTO listTokenRequestDTO) {
        Criteria<TokenSession> tokenSessionCriteria = new Criteria<>(LogicalOperator.OR);
        tokenSessionCriteria.add(Restrictions.eq("userId", listTokenRequestDTO.getValue()));
        return super.findAll(tokenSessionCriteria, listTokenRequestDTO.getSortDataList());
    }

    public PageResponseData<TokenResponseDTO> page(PageTokenRequestDTO pageTokenRequestDTO) {
        Criteria<TokenSession> tokenSessionCriteria = new Criteria<>(LogicalOperator.OR);
        tokenSessionCriteria.add(Restrictions.eq("userId", pageTokenRequestDTO.getValue()));
        return super.findAll(tokenSessionCriteria, pageTokenRequestDTO.getPageRequestData());
    }

    public TokenResponseDTO generate(TokenSession tokenSession) {
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        ESBeanUtils.copyProperties(tokenSession, tokenResponseDTO);
        tokenResponseDTO.setToken(tokenSession.getToken());
        tokenResponseDTO.setCreateTime(tokenSession.getCreateTime());
        return tokenResponseDTO;
    }
}
