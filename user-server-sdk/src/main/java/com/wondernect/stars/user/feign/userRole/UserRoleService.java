package com.wondernect.stars.user.feign.userRole;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;
import com.wondernect.stars.user.feign.userLocalAuth.UserLocalAuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserRoleService {

    @Autowired
    private UserRoleClient userRoleClient;

    public boolean create(UserRoleRequestDTO userRoleRequestDTO){
        BusinessData businessData = userRoleClient.create(userRoleRequestDTO);
        return businessData.success();
    }

    public boolean update(UserRoleRequestDTO userRoleRequestDTO){
        BusinessData businessData = userRoleClient.update(userRoleRequestDTO);
        return businessData.success();
    }

    public UserRoleResponseDTO detail(String userId, String roleId){
        BusinessData<UserRoleResponseDTO> businessData = userRoleClient.detail(userId,roleId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<UserRoleResponseDTO> list(ListUserRoleRequestDTO listUserRoleRequestDTO){
        BusinessData<List<UserRoleResponseDTO>> businessData = userRoleClient.list(listUserRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<UserRoleResponseDTO> page(PageUserRoleRequestDTO pageUserRoleRequestDTO){
        BusinessData<PageResponseData<UserRoleResponseDTO>> businessData = userRoleClient.page(pageUserRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
