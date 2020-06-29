package com.wondernect.stars.rbac.service.rolemenuoperation;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.OperationManager;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.RoleMenuOperationManager;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.model.Operation;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.RoleMenuOperation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuOperationService
 * Author: chenxun
 * Date: 2020-02-21 14:06
 * Description:
 */
public abstract class RoleMenuOperationAbstractService extends BaseStringService<RoleMenuOperationResponseDTO, RoleMenuOperation> implements RoleMenuOperationInterface {

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private OperationManager operationManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    @Override
    public void add(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        Role role = roleManager.findByCode(roleMenuOperationRequestDTO.getRoleCode());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findByCode(roleMenuOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = operationManager.findByCode(roleMenuOperationRequestDTO.getOperationCode(), roleMenuOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(roleMenuOperationRequestDTO.getRoleCode(), roleMenuOperationRequestDTO.getMenuCode(), roleMenuOperationRequestDTO.getOperationCode());
        if (ESObjectUtils.isNull(roleMenuOperation)) {
            super.saveEntity(
                    new RoleMenuOperation(
                            roleMenuOperationRequestDTO.getRoleCode(),
                            roleMenuOperationRequestDTO.getMenuCode(),
                            roleMenuOperationRequestDTO.getOperationCode()
                    )
            );
        }
    }

    @Transactional
    @Override
    public void edit(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        Role role = roleManager.findByCode(roleMenuOperationRequestDTO.getRoleCode());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findByCode(roleMenuOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = operationManager.findByCode(roleMenuOperationRequestDTO.getOperationCode(), roleMenuOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(roleMenuOperationRequestDTO.getRoleCode(), roleMenuOperationRequestDTO.getMenuCode(), roleMenuOperationRequestDTO.getOperationCode());
        if (ESObjectUtils.isNull(roleMenuOperation)) {
            throw new BusinessException("角色菜单操作关系不存在");
        }
        roleMenuOperation.setLimitable(roleMenuOperationRequestDTO.getLimitable());
        roleMenuOperation.setStartTime(roleMenuOperationRequestDTO.getStartTime());
        roleMenuOperation.setEndTime(roleMenuOperationRequestDTO.getEndTime());
        super.saveEntity(roleMenuOperation);
    }

    @Transactional
    @Override
    public void delete(RoleMenuOperationRequestDTO roleMenuOperationRequestDTO) {
        RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(roleMenuOperationRequestDTO.getRoleCode(), roleMenuOperationRequestDTO.getMenuCode(), roleMenuOperationRequestDTO.getOperationCode());
        if (ESObjectUtils.isNotNull(roleMenuOperation)) {
            super.deleteById(roleMenuOperation.getId());
        }
    }

    @Override
    public RoleMenuOperationResponseDTO findByRoleCodeAndMenuCodeAndOperationCode(String roleCode, String menuCode, String operationCode) {
        Operation operation = operationManager.findByCode(operationCode, menuCode);
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(roleCode, menuCode, operationCode);
        if (ESObjectUtils.isNull(roleMenuOperation)) {
            return new RoleMenuOperationResponseDTO(
                    operation.getId(),
                    operation.getCode(),
                    operation.getName(),
                    true,
                    roleMenuOperation.getLimitable(),
                    roleMenuOperation.getStartTime(),
                    roleMenuOperation.getEndTime()
            );
        } else {
            return new RoleMenuOperationResponseDTO(
                    operation.getId(),
                    operation.getCode(),
                    operation.getName(),
                    false,
                    null,
                    null,
                    null
            );
        }
    }

    @Override
    public List<RoleMenuOperationResponseDTO> list(ListRoleMenuOperationRequestDTO listRoleMenuOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuCode", listRoleMenuOperationRequestDTO.getMenuCode()));
        List<Operation> operationList = operationManager.findAll(operationCriteria, new ArrayList<>());
        List<RoleMenuOperationResponseDTO> roleMenuOperationResponseDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(operationList)) {
            for (Operation operation : operationList) {
                RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(listRoleMenuOperationRequestDTO.getRoleCode(), listRoleMenuOperationRequestDTO.getMenuCode(), operation.getCode());
                if (ESObjectUtils.isNotNull(roleMenuOperation)) {
                    roleMenuOperationResponseDTOList.add(
                            new RoleMenuOperationResponseDTO(
                                    operation.getId(),
                                    operation.getCode(),
                                    operation.getName(),
                                    true,
                                    roleMenuOperation.getLimitable(),
                                    roleMenuOperation.getStartTime(),
                                    roleMenuOperation.getEndTime()
                            )
                    );
                } else {
                    roleMenuOperationResponseDTOList.add(
                            new RoleMenuOperationResponseDTO(
                                    operation.getId(),
                                    operation.getCode(),
                                    operation.getName(),
                                    false,
                                    null,
                                    null,
                                    null
                            )
                    );
                }
            }
        }
        return roleMenuOperationResponseDTOList;
    }

    @Override
    public PageResponseData<RoleMenuOperationResponseDTO> page(PageRoleMenuOperationRequestDTO pageRoleMenuOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuCode", pageRoleMenuOperationRequestDTO.getMenuCode()));
        PageResponseData<Operation> operationPageResponseData = operationManager.findAll(operationCriteria, pageRoleMenuOperationRequestDTO.getPageRequestData());
        List<RoleMenuOperationResponseDTO> roleMenuOperationResponseDTOList = new ArrayList<>();
        List<Operation> operationList = operationPageResponseData.getDataList();
        if (CollectionUtils.isNotEmpty(operationList)) {
            for (Operation operation : operationList) {
                RoleMenuOperation roleMenuOperation = roleMenuOperationManager.findByRoleCodeAndMenuCodeAndOperationCode(pageRoleMenuOperationRequestDTO.getRoleCode(), pageRoleMenuOperationRequestDTO.getMenuCode(), operation.getCode());
                if (ESObjectUtils.isNotNull(roleMenuOperation)) {
                    roleMenuOperationResponseDTOList.add(
                            new RoleMenuOperationResponseDTO(
                                    operation.getId(),
                                    operation.getCode(),
                                    operation.getName(),
                                    true,
                                    roleMenuOperation.getLimitable(),
                                    roleMenuOperation.getStartTime(),
                                    roleMenuOperation.getEndTime()
                            )
                    );
                } else {
                    roleMenuOperationResponseDTOList.add(
                            new RoleMenuOperationResponseDTO(
                                    operation.getId(),
                                    operation.getCode(),
                                    operation.getName(),
                                    false,
                                    null,
                                    null,
                                    null
                            )
                    );
                }
            }
        }
        return new PageResponseData<>(
                operationPageResponseData.getPage(),
                operationPageResponseData.getSize(),
                operationPageResponseData.getTotalPages(),
                operationPageResponseData.getTotalElements(),
                roleMenuOperationResponseDTOList
        );
    }

    @Override
    public RoleMenuOperationResponseDTO generate(RoleMenuOperation roleMenuOperation) {
        return null;
    }
}
