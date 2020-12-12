package com.wondernect.stars.user.server.service;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.feign.role.RoleServerService;
import com.wondernect.stars.rbac.feign.roleType.RoleTypeServerService;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: UserServerService
 * Author: chenxun
 * Date: 2020/11/25 11:50
 * Description:
 */
@Service
public class UserServerService extends UserService {

    @Autowired
    private RoleTypeServerService roleTypeServerService;

    @Autowired
    private RoleServerService roleServerService;

    @Override
    public UserResponseDTO generate(User user) {
        UserResponseDTO userResponseDTO = super.generate(user);
        RoleTypeResponseDTO roleTypeResponseDTO = roleTypeServerService.detail(user.getRoleTypeId());
        userResponseDTO.setRoleTypeName(ESObjectUtils.isNotNull(roleTypeResponseDTO) ? roleTypeResponseDTO.getName() : null);
        RoleResponseDTO roleResponseDTO = roleServerService.detail(user.getRoleId());
        userResponseDTO.setRoleName(ESObjectUtils.isNotNull(roleResponseDTO) ? roleResponseDTO.getName() : null);
        return userResponseDTO;
    }
}
