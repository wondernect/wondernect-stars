package com.wondernect.stars.rbac.feign.role;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.MenuResponseDTO;
import com.wondernect.stars.rbac.dto.menu.SaveMenuRequestDTO;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.feign.menu.MenuFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class RoleServerService {

    @Autowired
    private RoleFeignClient roleFeignClient;

    public RoleResponseDTO create(SaveRoleRequestDTO saveRoleRequestDTO){
        BusinessData<RoleResponseDTO> businessData = roleFeignClient.create(saveRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public RoleResponseDTO update(String id,SaveRoleRequestDTO saveRoleRequestDTO){
        BusinessData<RoleResponseDTO> businessData = roleFeignClient.update(id,saveRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = roleFeignClient.delete(id);
        return businessData.success();
    }

    public RoleResponseDTO get(String id){
        BusinessData<RoleResponseDTO> businessData = roleFeignClient.get(id);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<RoleResponseDTO> list(ListRoleRequestDTO listRoleRequestDTO){
        BusinessData<List<RoleResponseDTO>> businessData = roleFeignClient.list(listRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<RoleResponseDTO> page(PageRoleRequestDTO pageRoleRequestDTO){
        BusinessData<PageResponseData<RoleResponseDTO>> businessData = roleFeignClient.page(pageRoleRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
