package com.wondernect.stars.session.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.code.*;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CodeSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface InitCodeSessionService {

    /**
     * 请求(缓存&数据库)
     */
    CodeResponseDTO requestCodeSession(CodeRequestDTO codeRequestDTO);

    /**
     * 删除(缓存&数据库)
     */
    void deleteByCode(String code);

    /**
     * 获取(缓存&数据库)
     */
    CodeResponseDTO findByCode(String code);

    /**
     * 删除(缓存)
     */
    void deleteCacheByCode(String code);

    /**
     * 获取(缓存)
     */
    CodeResponseDTO findCacheByCode(String code);

    /**
     * 续约/刷新(缓存&数据库)
     */
    CodeResponseDTO refreshCodeSession(CodeRefreshRequestDTO codeRefreshRequestDTO);

    /**
     * 验证(缓存)
     */
    CodeResponseDTO authCodeSession(CodeAuthRequestDTO codeAuthRequestDTO);

    /**
     * 列表(数据库)
     */
    List<CodeResponseDTO> list(ListCodeRequestDTO listCodeRequestDTO);

    /**
     * 分页(数据库)
     */
    PageResponseData<CodeResponseDTO> page(PageCodeRequestDTO pageCodeRequestDTO);
}
