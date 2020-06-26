package com.wondernect.stars.session.service.astract;

import com.wondernect.elements.common.utils.*;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.config.SessionConfigProperties;
import com.wondernect.stars.session.dto.captcha.*;
import com.wondernect.stars.session.manager.CaptchaSessionManager;
import com.wondernect.stars.session.model.CaptchaSession;
import com.wondernect.stars.session.service.InitCaptchaSessionService;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AbstractCaptchaSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
public abstract class AbstractCaptchaSessionService extends BaseStringService<CaptchaResponseDTO, CaptchaSession> implements InitCaptchaSessionService {

    @Autowired
    private SessionConfigProperties sessionConfigProperties;

    @Autowired
    private CaptchaSessionManager captchaSessionManager;

    public CaptchaResponseDTO requestCaptchaSession(CaptchaRequestDTO captchaRequestDTO) {
        Long expires = captchaRequestDTO.getExpires();
        if (ESObjectUtils.isNull(expires) || expires <= 0L) {
            expires = sessionConfigProperties.getCaptchaExpires();
        }
        CaptchaSession captchaSession;
        // captchaType 验证码类型: 0-数字; 1-字符;
        if (sessionConfigProperties.getCaptchaType() == 1) {
            captchaSession = new CaptchaSession(
                    captchaRequestDTO.getUsername(),
                    ESRandomUtils.randomStringWithChars(sessionConfigProperties.getCaptchaLength()),
                    captchaRequestDTO.getCaptchaImageFileId(),
                    captchaRequestDTO.getCaptchaImageFilePath(),
                    captchaRequestDTO.getDescription(),
                    expires,
                    captchaRequestDTO.getIp(),
                    captchaRequestDTO.getDevicePlatform(),
                    captchaRequestDTO.getDeviceDescription()
            );
        } else {
            captchaSession = new CaptchaSession(
                    captchaRequestDTO.getUsername(),
                    ESRandomUtils.randomNumberString(sessionConfigProperties.getCaptchaLength()),
                    captchaRequestDTO.getCaptchaImageFileId(),
                    captchaRequestDTO.getCaptchaImageFilePath(),
                    captchaRequestDTO.getDescription(),
                    expires,
                    captchaRequestDTO.getIp(),
                    captchaRequestDTO.getDevicePlatform(),
                    captchaRequestDTO.getDeviceDescription()
            );
        }
        return super.save(captchaSession);
    }

    public void deleteCacheById(String captchaSessionId) {
        CaptchaSession captchaSession = captchaSessionManager.findCacheById(captchaSessionId);
        if (ESObjectUtils.isNotNull(captchaSession)) {
            captchaSessionManager.deleteCacheById(captchaSessionId);
        }
    }

    public CaptchaResponseDTO findCacheById(String captchaSessionId) {
        CaptchaSession captchaSession = captchaSessionManager.findCacheById(captchaSessionId);
        if (ESObjectUtils.isNull(captchaSession)) {
            return null;
        }
        return generate(captchaSession);
    }

    public CaptchaResponseDTO authCacheCaptchaSession(CaptchaAuthRequestDTO captchaAuthRequestDTO) {
        CaptchaSession captchaSession = captchaSessionManager.findCacheById(captchaAuthRequestDTO.getCaptchaSessionId());
        if (ESObjectUtils.isNull(captchaSession)) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_NOT_FOUND);
        }
        if (ESDateTimeUtils.getCurrentTimestamp() > (captchaSession.getCreateTime() + captchaSession.getExpires())) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_CAPTCHA_EXPIRED);
        }
        if (!captchaAuthRequestDTO.getCaptcha().equalsIgnoreCase(captchaSession.getCaptcha())) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_CAPTCHA_INVALID);
        }
        if (ESStringUtils.isNotBlank(captchaAuthRequestDTO.getUsername()) && !captchaAuthRequestDTO.getUsername().equalsIgnoreCase(captchaSession.getUsername())) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_USERNAME_INVALID);
        }
        return generate(captchaSession);
    }

    public List<CaptchaResponseDTO> list(ListCaptchaRequestDTO listCaptchaRequestDTO) {
        Criteria<CaptchaSession> captchaSessionCriteria = new Criteria<>(LogicalOperator.OR);
        captchaSessionCriteria.add(Restrictions.like("username", listCaptchaRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(captchaSessionCriteria, listCaptchaRequestDTO.getSortDataList());
    }

    public PageResponseData<CaptchaResponseDTO> page(PageCaptchaRequestDTO pageCaptchaRequestDTO) {
        Criteria<CaptchaSession> captchaSessionCriteria = new Criteria<>(LogicalOperator.OR);
        captchaSessionCriteria.add(Restrictions.like("username", pageCaptchaRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(captchaSessionCriteria, pageCaptchaRequestDTO.getPageRequestData());
    }

    public CaptchaResponseDTO generate(CaptchaSession captchaSession) {
        CaptchaResponseDTO captchaResponseDTO = new CaptchaResponseDTO();
        ESBeanUtils.copyProperties(captchaSession, captchaResponseDTO);
        captchaResponseDTO.setCaptchaSessionId(captchaSession.getId());
        captchaResponseDTO.setCreateTime(captchaSession.getCreateTime());
        return captchaResponseDTO;
    }
}
