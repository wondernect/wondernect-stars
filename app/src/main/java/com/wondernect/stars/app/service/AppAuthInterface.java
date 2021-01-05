package com.wondernect.stars.app.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.auth.AppAuthResponseDTO;
import com.wondernect.stars.app.dto.auth.ListAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.PageAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.SaveAppAuthRequestDTO;

import java.util.List;

/**
 * 应用访问认证服务接口类
 *
 * @author chenxun 2020-12-29 16:28:23
 **/
public interface AppAuthInterface {

    /**
     * 创建
     **/
    AppAuthResponseDTO create(SaveAppAuthRequestDTO saveAppAuthRequestDTO);

    /**
     * 更新
     **/
    AppAuthResponseDTO update(String id, SaveAppAuthRequestDTO saveAppAuthRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    AppAuthResponseDTO findById(String id);

    /**
     * 获取详细信息(基础信息)
     **/
    AppAuthResponseDTO existByAppIdAndUserId(String appId, String userId);

    /**
     * 列表
     **/
    List<AppAuthResponseDTO> list(ListAppAuthRequestDTO listAppAuthRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<AppAuthResponseDTO> page(PageAppAuthRequestDTO pageAppAuthRequestDTO);
}