package com.wondernect.stars.session.service.captcha;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.captcha.*;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CaptchaSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface CaptchaSessionInterface {

    /**
     * 请求(缓存&数据库)
     */
    CaptchaResponseDTO requestCaptchaSession(CaptchaRequestDTO captchaRequestDTO);

    /**
     * 删除(缓存&数据库)
     */
    void deleteById(String captchaSessionId);

    /**
     * 获取(缓存&数据库)
     */
    CaptchaResponseDTO findById(String captchaSessionId);

    /**
     * 删除(缓存)
     */
    void deleteCacheById(String captchaSessionId);

    /**
     * 获取(缓存)
     */
    CaptchaResponseDTO findCacheById(String captchaSessionId);

    /**
     * 验证(缓存)
     */
    CaptchaResponseDTO authCacheCaptchaSession(CaptchaAuthRequestDTO captchaAuthRequestDTO);

    /**
     * 列表(数据库)
     */
    List<CaptchaResponseDTO> list(ListCaptchaRequestDTO listCaptchaRequestDTO);

    /**
     * 分页(数据库)
     */
    PageResponseData<CaptchaResponseDTO> page(PageCaptchaRequestDTO pageCaptchaRequestDTO);
}
