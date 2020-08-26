package com.wondernect.stars.user.feign.userRole;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class UserRoleServerService {

    @Autowired
    private UserRoleFeignClient userRoleFeignClient;

    public boolean create(UserRoleRequestDTO userRoleRequestDTO){
        BusinessData businessData = userRoleFeignClient.create(userRoleRequestDTO);
        return businessData.success();
    }

    public boolean delete(UserRoleRequestDTO userRoleRequestDTO){
        BusinessData businessData = userRoleFeignClient.delete(userRoleRequestDTO);
        return businessData.success();
    }

    public UserRoleResponseDTO detail(String userId, String roleId){
        BusinessData<UserRoleResponseDTO> businessData = userRoleFeignClient.detail(userId,roleId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<UserRoleResponseDTO> list(ListUserRoleRequestDTO listUserRoleRequestDTO){
        BusinessData<List<UserRoleResponseDTO>> businessData = userRoleFeignClient.list(listUserRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<UserRoleResponseDTO> page(PageUserRoleRequestDTO pageUserRoleRequestDTO){
        BusinessData<PageResponseData<UserRoleResponseDTO>> businessData = userRoleFeignClient.page(pageUserRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
