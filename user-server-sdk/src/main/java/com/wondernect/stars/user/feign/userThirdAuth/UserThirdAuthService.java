package com.wondernect.stars.user.feign.userThirdAuth;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.feign.userRole.UserRoleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserThirdAuthService {

    @Autowired
    private UserThirdAuthClient userThirdAuthClient;

    public UserThirdAuthResponseDTO create(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthClient.create(userId,saveUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserThirdAuthResponseDTO update(String userId,SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthClient.update(userId,saveUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String userId, AppType appType) {
        BusinessData businessData = userThirdAuthClient.delete(userId,appType);
        return businessData.success();
    }

    public UserThirdAuthResponseDTO detail(String userId,AppType appType){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthClient.detail(userId,appType);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }


    public UserThirdAuthResponseDTO detailByAppTypeAndAppUserId(AppType appType,String appUserId){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthClient.detailByAppTypeAndAppUserId(appType,appUserId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserThirdAuthResponseDTO list(AuthUserThirdAuthRequestDTO authUserThirdAuthRequestDTO){
        BusinessData<UserThirdAuthResponseDTO> businessData = userThirdAuthClient.list(authUserThirdAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
