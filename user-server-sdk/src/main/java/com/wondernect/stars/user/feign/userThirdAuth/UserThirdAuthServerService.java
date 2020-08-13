package com.wondernect.stars.user.feign.userThirdAuth;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.em.AppType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserThirdAuthServerService {

    @Autowired
    private UserThirdAuthFeignClient userThirdAuthFeignClient;

    public UserThirdAuthResponseDTO create(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthFeignClient.create(userId,saveUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserThirdAuthResponseDTO update(String userId,SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthFeignClient.update(userId,saveUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String userId, AppType appType) {
        BusinessData businessData = userThirdAuthFeignClient.delete(userId,appType);
        return businessData.success();
    }

    public UserThirdAuthResponseDTO detail(String userId,AppType appType){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthFeignClient.detail(userId,appType);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }


    public UserThirdAuthResponseDTO detailByAppTypeAndAppUserId(AppType appType,String appUserId){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthFeignClient.detailByAppTypeAndAppUserId(appType,appUserId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserThirdAuthResponseDTO list(AuthUserThirdAuthRequestDTO authUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthFeignClient.list(authUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
