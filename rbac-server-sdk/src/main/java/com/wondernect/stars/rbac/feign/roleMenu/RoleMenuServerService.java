package com.wondernect.stars.rbac.feign.roleMenu;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.RoleAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;
import com.wondernect.stars.rbac.feign.role.RoleFeignClient;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class RoleMenuServerService {

    @Autowired
    private RoleMenuFeignClient roleMenuFeignClient;

    public boolean create(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.create(roleMenuRequestDTO);
        return businessData.success();
    }

    public boolean update(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.update(roleMenuRequestDTO);
        return businessData.success();
    }

    public boolean delete(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.delete(roleMenuRequestDTO);
        return businessData.success();
    }

    public RoleMenuResponseDTO detail(String roleId, String menuId) {
        BusinessData<RoleMenuResponseDTO> businessData = roleMenuFeignClient.detail(roleId, menuId);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public RoleMenuTreeResponseDTO tree(String roleId, String menuId) {
        BusinessData<RoleMenuTreeResponseDTO> businessData = roleMenuFeignClient.tree(roleId, menuId);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public RoleAuthorityResponseDTO roleAuthority(String roleId) {
        BusinessData<RoleAuthorityResponseDTO> businessData = roleMenuFeignClient.roleAuthority(roleId);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }

    public List<MenuAuthorityResponseDTO> roleAuthority(List<String> roleIdList){
        BusinessData<List<MenuAuthorityResponseDTO>> businessData = roleMenuFeignClient.roleAuthority(roleIdList);
        if (!businessData.success()){
            return null;
        }
        return businessData.getData();
    }
}
