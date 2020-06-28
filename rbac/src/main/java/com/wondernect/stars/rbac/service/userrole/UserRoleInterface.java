package com.wondernect.stars.rbac.service.userrole;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.rbac.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.rbac.dto.userrole.UserRoleResponseDTO;

import java.util.List;

/**
 * 用户角色服务接口类
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
public interface UserRoleInterface {

    /**
     * 创建
     **/
    UserRoleResponseDTO add(String userId, String role);

    /**
     * 删除
     **/
    void delete(String userId, String role);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    UserRoleResponseDTO findById(String id);

    /**
     * 获取详细信息
     **/
    UserRoleResponseDTO findByUserIdAndRole(String userId, String role);

    /**
     * 列表
     **/
    List<UserRoleResponseDTO> list(ListUserRoleRequestDTO listUserRoleRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<UserRoleResponseDTO> page(PageUserRoleRequestDTO pageUserRoleRequestDTO);
}