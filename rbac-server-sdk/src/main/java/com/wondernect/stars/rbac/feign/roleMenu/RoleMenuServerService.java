package com.wondernect.stars.rbac.feign.roleMenu;

import com.wondernect.elements.common.exception.BusinessException;
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

    public boolean add(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.add(roleMenuRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public boolean edit(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.edit(roleMenuRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public boolean delete(RoleMenuRequestDTO roleMenuRequestDTO) {
        BusinessData businessData = roleMenuFeignClient.delete(roleMenuRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public RoleMenuResponseDTO detail(String roleId, String menuId) {
        BusinessData<RoleMenuResponseDTO> businessData = roleMenuFeignClient.detail(roleId, menuId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public RoleMenuTreeResponseDTO tree(String roleId, String menuId) {
        BusinessData<RoleMenuTreeResponseDTO> businessData = roleMenuFeignClient.tree(roleId, menuId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public RoleAuthorityResponseDTO roleAuthority(String roleId) {
        BusinessData<RoleAuthorityResponseDTO> businessData = roleMenuFeignClient.roleAuthority(roleId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<MenuAuthorityResponseDTO> roleListAuthority(List<String> roleIdList){
        BusinessData<List<MenuAuthorityResponseDTO>> businessData = roleMenuFeignClient.roleListAuthority(roleIdList);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
