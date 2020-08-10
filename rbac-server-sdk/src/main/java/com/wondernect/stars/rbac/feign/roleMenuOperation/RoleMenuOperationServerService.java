package com.wondernect.stars.rbac.feign.roleMenuOperation;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class RoleMenuOperationServerService {

    @Autowired
    private RoleMenuOperationFeignClient roleMenuOperationFeignClient;

    public boolean add(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        BusinessData businessData = roleMenuOperationFeignClient.add(roleMenuOperationRequestDTO);
        return businessData.success();
    }

    public boolean edit(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        BusinessData businessData = roleMenuOperationFeignClient.edit(roleMenuOperationRequestDTO);
        return businessData.success();
    }

    public boolean delete(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        BusinessData businessData = roleMenuOperationFeignClient.delete(roleMenuOperationRequestDTO);
        return businessData.success();
    }

    public RoleMenuOperationResponseDTO getRoleMenuOperation(String roleId, String menuId, String operationId) {
        BusinessData<RoleMenuOperationResponseDTO> businessData = roleMenuOperationFeignClient.getRoleMenuOperation(roleId, menuId,operationId);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public List<RoleMenuOperationResponseDTO> list(ListRoleMenuOperationRequestDTO listRoleMenuOperationRequestDTO) {
        BusinessData<List<RoleMenuOperationResponseDTO>> businessData = roleMenuOperationFeignClient.list(listRoleMenuOperationRequestDTO);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<RoleMenuOperationResponseDTO> page(PageRoleMenuOperationRequestDTO pageRoleMenuOperationRequestDTO){
        BusinessData<PageResponseData<RoleMenuOperationResponseDTO>> businessData = roleMenuOperationFeignClient.page(pageRoleMenuOperationRequestDTO);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }
}
