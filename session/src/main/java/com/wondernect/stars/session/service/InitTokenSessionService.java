package com.wondernect.stars.session.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.token.*;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: TokenSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface InitTokenSessionService {

    /**
     * 请求(缓存&数据库)
     */
    TokenResponseDTO requestTokenSession(TokenRequestDTO tokenRequestDTO);

    /**
     * 删除(缓存&数据库)
     */
    void deleteByToken(String token);

    /**
     * 获取(缓存&数据库)
     */
    TokenResponseDTO findByToken(String token);

    /**
     * 刷新(缓存&数据库)
     */
    TokenResponseDTO refreshTokenSession(TokenRefreshRequestDTO tokenRefreshRequestDTO);

    /**
     * 验证(缓存&数据库)
     */
    TokenResponseDTO authTokenSession(TokenAuthRequestDTO tokenAuthRequestDTO);

    /**
     * 列表(数据库)
     */
    List<TokenResponseDTO> list(ListTokenRequestDTO listTokenRequestDTO);

    /**
     * 分页(数据库)
     */
    PageResponseData<TokenResponseDTO> page(PageTokenRequestDTO pageTokenRequestDTO);
}
