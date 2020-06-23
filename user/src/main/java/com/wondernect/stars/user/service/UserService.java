package com.wondernect.stars.user.service;

import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.model.OpenUser;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.model.UserAuth;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserAPI
 * Author: chenxun
 * Date: 2019/3/17 18:57
 * Description: user api
 */
public interface UserService {

    /**
     * 创建用户
     */
    User userRegist(User user, OpenUser openUser, UserAuth userAuth);

    /**
     * 更新用户信息
     */
    User updateUser(User user);

    /**
     * 删除用户
     */
    void deleteByUserId(String operator, String userId);

    /**
     * 获取本人或其他用户信息
     */
    User findByUserId(String userId);

    /**
     * 获取本人或其他用户信息(根据手机号码或邮箱获取用户信息)
     */
    User findByUsername(String username);

    /**
     * 密码认证
     */
    void authUserPassword(UserAuth userAuth);

    /**
     * 密码设置/密码重置/密码修改
     */
    void setUserPassword(UserAuth userAuth);

    /**
     * 获取用户列表
     */
    List<User> findList(String createUser, String name, String mobile, String email, List<SortData> sortDataList);

    /**
     * 获取用户分页
     */
    PageResponseData<User> findPage(String createUser, String name, String mobile, String email, PageRequestData pageRequestData);
}
