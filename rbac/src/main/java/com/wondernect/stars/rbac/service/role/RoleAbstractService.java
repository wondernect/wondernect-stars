package com.wondernect.stars.rbac.service.role;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.manager.*;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleService
 * Author: chenxun
 * Date: 2020-02-21 13:56
 * Description:
 */
public abstract class RoleAbstractService extends BaseStringService<RoleResponseDTO, Role> implements RoleInterface {

    @Autowired
    private RoleTypeManager roleTypeManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    public RoleResponseDTO create(SaveRoleRequestDTO saveRoleRequestDTO) {
        RoleType roleType = roleTypeManager.findById(saveRoleRequestDTO.getRoleTypeId());
        if (ESObjectUtils.isNull(roleType)) {
            throw new BusinessException("角色类型不存在");
        }
        if (ESObjectUtils.isNull(saveRoleRequestDTO.getWeight())) {
            saveRoleRequestDTO.setWeight(0);
        }
        return super.save(
                new Role(
                        saveRoleRequestDTO.getName(),
                        saveRoleRequestDTO.getDescription(),
                        saveRoleRequestDTO.getEditable(),
                        saveRoleRequestDTO.getDeletable(),
                        saveRoleRequestDTO.getWeight(),
                        saveRoleRequestDTO.getRoleTypeId()
                )
        );
    }

    @Transactional
    public RoleResponseDTO update(String id, SaveRoleRequestDTO saveRoleRequestDTO) {
        RoleType roleType = roleTypeManager.findById(saveRoleRequestDTO.getRoleTypeId());
        if (ESObjectUtils.isNull(roleType)) {
            throw new BusinessException("角色类型不存在");
        }
        Role role = super.findEntityById(id);
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        if (!role.getEditable()) {
            throw new BusinessException("角色不可编辑");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveRoleRequestDTO, role);
        return super.save(role);
    }

    @Transactional
    public void deleteById(String id) {
        Role role = super.findEntityById(id);
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        if (!role.getDeletable()) {
            throw new BusinessException("角色不可删除");
        }
        roleMenuManager.deleteAllByRoleId(role.getId());
        roleMenuOperationManager.deleteAllByRoleId(role.getId());
        super.deleteById(id);
    }

    public List<RoleResponseDTO> list(ListRoleRequestDTO listRoleRequestDTO) {
        Criteria<Role> roleCriteria = new Criteria<>();
        roleCriteria.add(Restrictions.eq("roleTypeId", listRoleRequestDTO.getRoleTypeId()));
        return super.findAll(roleCriteria, listRoleRequestDTO.getSortDataList());
    }

    public PageResponseData<RoleResponseDTO> page(PageRoleRequestDTO pageRoleRequestDTO) {
        Criteria<Role> roleCriteria = new Criteria<>();
        roleCriteria.add(Restrictions.eq("roleTypeId", pageRoleRequestDTO.getRoleTypeId()));
        return super.findAll(roleCriteria, pageRoleRequestDTO.getPageRequestData());
    }

    public RoleResponseDTO generate(Role role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        ESBeanUtils.copyProperties(role, roleResponseDTO);
        RoleType roleType = roleTypeManager.findById(role.getRoleTypeId());
        roleResponseDTO.setRoleTypeId(ESObjectUtils.isNotNull(roleType) ? roleType.getId() : null);
        roleResponseDTO.setRoleTypeName(ESObjectUtils.isNotNull(roleType) ? roleType.getName() : null);
        return roleResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return null;
    }
}
