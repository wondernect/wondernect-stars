package com.wondernect.stars.user.feign.user;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.SaveUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserResponseDTO create(SaveUserRequestDTO saveUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userFeignClient.create(saveUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean enable(String userId) {
        BusinessData businessData = userFeignClient.enable(userId);
        return businessData.success();
    }

    public boolean disable(String userId) {
        BusinessData businessData = userFeignClient.disable(userId);
        return businessData.success();
    }


    public UserResponseDTO update(String userId,SaveUserRequestDTO saveUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userFeignClient.update(userId,saveUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String userId) {
        BusinessData businessData = userFeignClient.delete(userId);
        return businessData.success();
    }

    public UserResponseDTO detail(String userId){
        BusinessData<UserResponseDTO> businessData = userFeignClient.detail(userId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }


    public UserResponseDTO detailByUsername(String username){
        BusinessData<UserResponseDTO> businessData = userFeignClient.detailByUsername(username);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO){
        BusinessData<List<UserResponseDTO>> businessData = userFeignClient.list(listUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO){
        BusinessData<PageResponseData<UserResponseDTO>> businessData = userFeignClient.page(pageUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
