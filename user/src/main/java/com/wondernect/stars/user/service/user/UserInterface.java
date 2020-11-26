package com.wondernect.stars.user.service.user;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.em.AppType;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserAPI
 * Author: chenxun
 * Date: 2019/3/17 18:57
 * Description: user api
 */
public interface UserInterface {

    /**
     * 创建 local user
     */
    UserResponseDTO createLocalUser(SaveLocalUserRequestDTO saveLocalUserRequestDTO);

    /**
     * 创建 third user
     */
    UserResponseDTO createThirdUser(SaveThirdUserRequestDTO saveThirdUserRequestDTO);

    /**
     * 更新
     */
    UserResponseDTO update(String userId, SaveLocalUserRequestDTO saveUserRequestDTO);

    /**
     * 激活/禁用
     */
    void enable(String userId, Boolean enable);

    /**
     * 删除
     */
    void deleteById(String userId);

    /**
     * 获取
     */
    UserResponseDTO findById(String userId);

    /**
     * 获取
     */
    UserResponseDTO findByMobile(String mobile);

    /**
     * 获取
     */
    UserResponseDTO findByEmail(String email);

    /**
     * 获取
     */
    UserResponseDTO findByUsername(String username);

    /**
     * 获取
     */
    UserResponseDTO findByAppTypeAndAppUserId(AppType appType, String appUserId);

    /**
     * 列表
     */
    List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO);

    /**
     * 分页
     */
    PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO);
}
