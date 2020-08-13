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
public class UserService {

    @Autowired
    private UserClient userClient;

    public UserResponseDTO create(SaveUserRequestDTO saveUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userClient.create(saveUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public UserResponseDTO update(String userId,SaveUserRequestDTO saveUserRequestDTO){
        BusinessData<UserResponseDTO> businessData = userClient.update(userId,saveUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String userId) {
        BusinessData businessData = userClient.delete(userId);
        return businessData.success();
    }

    public UserResponseDTO detail(String userId){
        BusinessData<UserResponseDTO> businessData = userClient.detail(userId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }


    public UserResponseDTO detailByUsername(String username){
        BusinessData<UserResponseDTO> businessData = userClient.detailByUsername(username);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO){
        BusinessData<List<UserResponseDTO>> businessData = userClient.list(listUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO){
        BusinessData<PageResponseData<UserResponseDTO>> businessData = userClient.page(pageUserRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
