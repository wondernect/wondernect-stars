package com.wondernect.stars.user.service.userrole;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;

import java.util.List;

/**
 * 用户角色服务接口类
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
public interface UserRoleInterface {

    /**
     * 新增
     **/
    void add(UserRoleRequestDTO userRoleRequestDTO);

    /**
     * 删除
     **/
    void delete(UserRoleRequestDTO userRoleRequestDTO);

    /**
     * 获取
     **/
    UserRoleResponseDTO findByUserIdAndRoleId(String userId, String roleId);

    /**
     * 列表
     **/
    List<UserRoleResponseDTO> list(ListUserRoleRequestDTO listUserRoleRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<UserRoleResponseDTO> page(PageUserRoleRequestDTO pageUserRoleRequestDTO);
}