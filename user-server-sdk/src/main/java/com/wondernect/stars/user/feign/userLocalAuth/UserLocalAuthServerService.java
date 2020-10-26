package com.wondernect.stars.user.feign.userLocalAuth;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserLocalAuthServerService {

    @Autowired
    private UserLocalAuthFeignClient userLocalAuthFeignClient;

    public UserLocalAuthResponseDTO create(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthFeignClient.create(userId,saveUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public UserLocalAuthResponseDTO update(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthFeignClient.update(userId,saveUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String userId) {
        BusinessData businessData = userLocalAuthFeignClient.delete(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public UserLocalAuthResponseDTO detail(String userId){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthFeignClient.detail(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public UserLocalAuthResponseDTO auth(String userId,AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO){
        BusinessData<UserLocalAuthResponseDTO> businessData = userLocalAuthFeignClient.auth(userId,authUserLocalAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
