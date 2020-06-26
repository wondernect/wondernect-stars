package com.wondernect.stars.rbac.service.astract;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.SaveOperationRequestDTO;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.OperationManager;
import com.wondernect.stars.rbac.manager.RoleMenuOperationManager;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.model.Operation;
import com.wondernect.stars.rbac.service.InitOperationService;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: OperationService
 * Author: chenxun
 * Date: 2020-02-21 14:05
 * Description:
 */
public abstract class AbstractOperationService extends BaseStringService<OperationResponseDTO, Operation> implements InitOperationService {

    @Autowired
    private OperationManager operationManager;

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    public OperationResponseDTO create(SaveOperationRequestDTO saveOperationRequestDTO) {
        Menu menu = menuManager.findByCode(saveOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = operationManager.findByCode(saveOperationRequestDTO.getCode(), saveOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNotNull(operation)) {
            throw new BusinessException("菜单下对应操作已存在");
        }
        if (ESObjectUtils.isNull(saveOperationRequestDTO.getWeight())) {
            saveOperationRequestDTO.setWeight(0);
        }
        return super.save(
                new Operation(
                        saveOperationRequestDTO.getCode(),
                        saveOperationRequestDTO.getName(),
                        saveOperationRequestDTO.getDescription(),
                        saveOperationRequestDTO.getEditable(),
                        saveOperationRequestDTO.getDeletable(),
                        saveOperationRequestDTO.getWeight(),
                        saveOperationRequestDTO.getMenuCode()
                )
        );
    }

    @Transactional
    public OperationResponseDTO update(String id, SaveOperationRequestDTO saveOperationRequestDTO) {
        Menu menu = menuManager.findByCode(saveOperationRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = super.findEntityById(id);
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        operation.setName(saveOperationRequestDTO.getName());
        operation.setDescription(saveOperationRequestDTO.getDescription());
        operation.setEditable(saveOperationRequestDTO.getEditable());
        operation.setDeletable(saveOperationRequestDTO.getDeletable());
        if (ESObjectUtils.isNotNull(saveOperationRequestDTO.getWeight())) {
            operation.setWeight(saveOperationRequestDTO.getWeight());
        }
        operation.setMenuCode(saveOperationRequestDTO.getMenuCode());
        return super.save(operation);
    }

    @Transactional
    public void deleteById(String id) {
        Operation operation = super.findEntityById(id);
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        if (!operation.getEditable()) {
            throw new BusinessException("操作不可删除");
        }
        roleMenuOperationManager.deleteAllByOperationCode(operation.getCode());
        super.deleteById(id);
    }

    public List<OperationResponseDTO> list(ListOperationRequestDTO listOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(
                Restrictions.and(
                        Restrictions.eq("menuCode", listOperationRequestDTO.getMenuCode()),
                        Restrictions.or(
                                Restrictions.like("code", listOperationRequestDTO.getValue(), MatchMode.ANYWHERE),
                                Restrictions.like("name", listOperationRequestDTO.getValue(), MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(operationCriteria, listOperationRequestDTO.getSortDataList());
    }

    public PageResponseData<OperationResponseDTO> page(PageOperationRequestDTO pageOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(
                Restrictions.and(
                        Restrictions.eq("menuCode", pageOperationRequestDTO.getMenuCode()),
                        Restrictions.or(
                                Restrictions.like("code", pageOperationRequestDTO.getValue(), MatchMode.ANYWHERE),
                                Restrictions.like("name", pageOperationRequestDTO.getValue(), MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(operationCriteria, pageOperationRequestDTO.getPageRequestData());
    }

    public OperationResponseDTO generate(Operation operation) {
        Menu menu = menuManager.findByCode(operation.getMenuCode());
        return new OperationResponseDTO(
                operation.getId(),
                operation.getCode(),
                operation.getName(),
                operation.getDescription(),
                operation.getEditable(),
                operation.getDeletable(),
                operation.getWeight(),
                operation.getMenuCode(),
                ESObjectUtils.isNotNull(menu) ? menu.getName() : null
        );
    }
}
