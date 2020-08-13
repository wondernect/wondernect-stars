package com.wondernect.stars.user.feign.userLocalAuth;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.SaveUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;
import com.wondernect.stars.user.feign.user.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserLocalAuthService {

    @Autowired
    private UserLocalAuthClient userLocalAuthClient;

    public UserLocalAuthResponseDTO create(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthClient.create(userId,saveUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserLocalAuthResponseDTO update(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthClient.update(userId,saveUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String userId) {
        BusinessData businessData = userLocalAuthClient.delete(userId);
        return businessData.success();
    }

    public UserLocalAuthResponseDTO detail(String userId){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthClient.detail(userId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserLocalAuthResponseDTO list(String userId,AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthClient.list(userId,authUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
