package com.wondernect.stars.app.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.*;

import java.util.List;

/**
 * 应用服务接口类
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
public interface AppInterface {

    /**
     * 创建
     **/
    AppResponseDTO create(SaveAppRequestDTO saveAppRequestDTO);

    /**
     * 更新
     **/
    AppResponseDTO update(String id, SaveAppRequestDTO saveAppRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    AppResponseDTO findById(String id);

    /**
     * 认证密钥
     */
    void auth(String id, AuthAppRequestDTO authAppRequestDTO);

    /**
     * 列表
     **/
    List<AppResponseDTO> list(ListAppRequestDTO listAppRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<AppResponseDTO> page(PageAppRequestDTO pageAppRequestDTO);
}