package com.wondernect.stars.rbac.feign.roleType;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.MenuResponseDTO;
import com.wondernect.stars.rbac.dto.menu.SaveMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;
import com.wondernect.stars.rbac.feign.roleMenu.RoleMenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class RoleTypeServerService {

    @Autowired
    private RoleTypeFeignClient roleTypeFeignClient;

    public RoleTypeResponseDTO create(SaveRoleTypeRequestDTO saveRoleTypeRequestDTO){
        BusinessData<RoleTypeResponseDTO> businessData = roleTypeFeignClient.create(saveRoleTypeRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public RoleTypeResponseDTO update(String id,SaveRoleTypeRequestDTO saveRoleTypeRequestDTO){
        BusinessData<RoleTypeResponseDTO> businessData = roleTypeFeignClient.update(id,saveRoleTypeRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = roleTypeFeignClient.delete(id);
        return businessData.success();
    }

    public RoleTypeResponseDTO get(String id) {
        BusinessData<RoleTypeResponseDTO> businessData = roleTypeFeignClient.get(id);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public List<RoleTypeResponseDTO> list(ListRoleTypeRequestDTO listRoleTypeRequestDTO) {
        BusinessData<List<RoleTypeResponseDTO>> businessData = roleTypeFeignClient.list(listRoleTypeRequestDTO);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<RoleTypeResponseDTO> page(PageRoleTypeRequestDTO pageRoleTypeRequestDTO){
        BusinessData<PageResponseData<RoleTypeResponseDTO>> businessData = roleTypeFeignClient.page(pageRoleTypeRequestDTO);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

}
