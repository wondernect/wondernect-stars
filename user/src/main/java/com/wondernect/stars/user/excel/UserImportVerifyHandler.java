package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelImportVerifyHandler;
import com.wondernect.stars.rbac.feign.role.RoleServerService;
import com.wondernect.stars.rbac.feign.roleType.RoleTypeServerService;
import com.wondernect.stars.user.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserVerifyHandler
 * Author: chenxun
 * Date: 2020-11-12 15:19
 * Description:
 */
@Service
public class UserImportVerifyHandler extends ESExcelImportVerifyHandler {

    @Autowired
    private UserManager userManager;

    @Autowired
    private RoleTypeServerService roleTypeServerService;

    @Autowired
    private RoleServerService roleServerService;

    @Override
    public BusinessData verifyData(Map<String, Object> object) {
        Object userType = object.get("userType");
        if (ESObjectUtils.isNotNull(userType)) {
            if (!ESStringUtils.equals("本地用户", userType.toString()) && !ESStringUtils.equals("第三方用户", userType.toString())) {
                return new BusinessData("用户类型有误");
            }
        } else {
            return new BusinessData("用户类型不能为空");
        }

        Object username = object.get("username");
        if (ESObjectUtils.isNotNull(username)) {
            if (ESObjectUtils.isNotNull(userManager.findByUsername(username.toString()))) {
                return new BusinessData("用户登录名已存在");
            }
        } else {
            return new BusinessData("用户登录名不能为空");
        }

        Object name = object.get("name");
        if (ESObjectUtils.isNull(name)) {
            return new BusinessData("姓名不能为空");
        }

        Object gender = object.get("gender");
        if (ESObjectUtils.isNotNull(gender)) {
            if (!ESStringUtils.equals("男", gender.toString()) && !ESStringUtils.equals("女", gender.toString()) && !ESStringUtils.equals("未知", gender.toString())) {
                return new BusinessData("性别有误");
            }
        }

        Object mobile = object.get("mobile");
        if (ESObjectUtils.isNotNull(mobile)) {
            if (!ESRegexUtils.isMobile(mobile.toString())) {
                return new BusinessData("手机号码格式有误");
            }
        }

        Object email = object.get("email");
        if (ESObjectUtils.isNotNull(email)) {
            if (!ESRegexUtils.isEmail(email.toString())) {
                return new BusinessData("邮箱格式有误");
            }
        }

        Object roleTypeId = object.get("roleTypeId");
        if (ESObjectUtils.isNotNull(roleTypeId)) {
            if (ESObjectUtils.isNull(roleTypeServerService.get(roleTypeId.toString()))) {
                return new BusinessData("角色类型id不存在");
            }
        } else {
            return new BusinessData("角色类型id不能为空");
        }

        Object roleId = object.get("roleId");
        if (ESObjectUtils.isNotNull(roleId)) {
            if (ESObjectUtils.isNull(roleServerService.get(roleId.toString()))) {
                return new BusinessData("角色id不存在");
            }
        } else {
            return new BusinessData("角色id不能为空");
        }

        Object enable = object.get("enable");
        if (ESObjectUtils.isNotNull(enable)) {
            if (!ESStringUtils.equals("是", enable.toString()) && !ESStringUtils.equals("否", enable.toString())) {
                return new BusinessData("是否可用有误");
            }
        }

        Object editable = object.get("editable");
        if (ESObjectUtils.isNotNull(editable)) {
            if (!ESStringUtils.equals("是", editable.toString()) && !ESStringUtils.equals("否", editable.toString())) {
                return new BusinessData("是否可编辑有误");
            }
        }

        Object deletable = object.get("deletable");
        if (ESObjectUtils.isNotNull(deletable)) {
            if (!ESStringUtils.equals("是", deletable.toString()) && !ESStringUtils.equals("否", deletable.toString())) {
                return new BusinessData("是否可删除有误");
            }
        }

        return new BusinessData(BusinessError.SUCCESS);
    }
}
