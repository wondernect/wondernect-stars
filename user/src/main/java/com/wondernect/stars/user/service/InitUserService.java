package com.wondernect.stars.user.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.SaveUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserAPI
 * Author: chenxun
 * Date: 2019/3/17 18:57
 * Description: user api
 */
public interface InitUserService {

    /**
     * 创建
     */
    UserResponseDTO create(SaveUserRequestDTO saveUserRequestDTO);

    /**
     * 更新
     */
    UserResponseDTO update(String userId, SaveUserRequestDTO saveUserRequestDTO);

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
    UserResponseDTO findByUsername(String username);

    /**
     * 列表
     */
    List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO);

    /**
     * 分页
     */
    PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO);
}
