package com.wondernect.stars.rbac.service.userrole;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.rbac.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.rbac.dto.userrole.UserRoleResponseDTO;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.UserRoleManager;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色服务抽象实现类
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
public abstract class UserRoleAbstractService extends BaseStringService<UserRoleResponseDTO, UserRole> implements UserRoleInterface {

    @Autowired
    private UserRoleManager userRoleManager;

    @Autowired
    private RoleManager roleManager;

    @Transactional
    @Override
    public UserRoleResponseDTO add(String userId, String role) {
        Role roleEntity = roleManager.findByCode(role);
        if (ESObjectUtils.isNull(roleEntity)) {
            throw new BusinessException("角色不存在");
        }
        UserRole userRole = userRoleManager.findByUserIdAndRole(userId, role);
        if (ESObjectUtils.isNotNull(userRole)) {
            throw new BusinessException("用户角色已存在");
        }
        userRole = new UserRole(userId, roleEntity.getRoleType(), role);
        return super.save(userRole);
    }

    @Transactional
    @Override
    public void delete(String userId, String role) {
        UserRole userRole = userRoleManager.findByUserIdAndRole(userId, role);
        if (ESObjectUtils.isNotNull(userRole)) {
            super.deleteById(userRole.getId());
        }
    }

    @Override
    public UserRoleResponseDTO findByUserIdAndRole(String userId, String role) {
        UserRole userRole = userRoleManager.findByUserIdAndRole(userId, role);
        if (ESObjectUtils.isNull(userRole)) {
            return null;
        }
        return generate(userRole);
    }

    @Override
    public List<UserRoleResponseDTO> list(ListUserRoleRequestDTO listUserRoleRequestDTO) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("userId", listUserRoleRequestDTO.getUserId()));
        return super.findAll(userRoleCriteria, listUserRoleRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<UserRoleResponseDTO> page(PageUserRoleRequestDTO pageUserRoleRequestDTO) {
        Criteria<UserRole> userRoleCriteria = new Criteria<>();
        userRoleCriteria.add(Restrictions.eq("userId", pageUserRoleRequestDTO.getUserId()));
        return super.findAll(userRoleCriteria, pageUserRoleRequestDTO.getPageRequestData());
    }

    @Override
    public UserRoleResponseDTO generate(UserRole userRole) {
        UserRoleResponseDTO userRoleResponseDTO = new UserRoleResponseDTO();
        ESBeanUtils.copyProperties(userRole, userRoleResponseDTO);
        userRoleResponseDTO.setId(userRole.getId());
        return userRoleResponseDTO;
    }
}