package com.wondernect.stars.user.feign.user;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.em.AppType;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserServerService {

    @Autowired
    private UserFeignClient userFeignClient;

    public UserResponseDTO create(SaveLocalUserRequestDTO saveLocalUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userFeignClient.create(saveLocalUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public UserResponseDTO createThirdUser(SaveThirdUserRequestDTO saveThirdUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userFeignClient.createThirdUser(saveThirdUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean enable(String userId) {
        BusinessData businessData = userFeignClient.enable(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public boolean disable(String userId) {
        BusinessData businessData = userFeignClient.disable(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }


    public UserResponseDTO update(String userId, SaveLocalUserRequestDTO saveLocalUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userFeignClient.update(userId, saveLocalUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String userId) {
        BusinessData businessData = userFeignClient.delete(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public UserResponseDTO detail(String userId){
        BusinessData<UserResponseDTO> businessData = userFeignClient.detail(userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public UserResponseDTO detailByUsername(String username){
        BusinessData<UserResponseDTO> businessData = userFeignClient.detailByUsername(username);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public UserResponseDTO detailByAppTypeAndAppUserId(AppType appType, String appUserId) {
        BusinessData<UserResponseDTO> businessData = userFeignClient.detailByAppTypeAndAppUserId(appType, appUserId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO){
        BusinessData<List<UserResponseDTO>> businessData = userFeignClient.list(listUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO){
        BusinessData<PageResponseData<UserResponseDTO>> businessData = userFeignClient.page(pageUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
