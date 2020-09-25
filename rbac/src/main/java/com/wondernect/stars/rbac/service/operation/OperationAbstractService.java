package com.wondernect.stars.rbac.service.operation;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
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
public abstract class OperationAbstractService extends BaseStringService<OperationResponseDTO, Operation> implements OperationInterface {

    @Autowired
    private OperationManager operationManager;

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    public OperationResponseDTO create(SaveOperationRequestDTO saveOperationRequestDTO) {
        Menu menu = menuManager.findById(saveOperationRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = operationManager.findByOperationCodeAndMenuId(saveOperationRequestDTO.getCode(), saveOperationRequestDTO.getMenuId());
        if (ESObjectUtils.isNotNull(operation)) {
            throw new BusinessException("菜单下对应操作代码已存在");
        }
        if (ESObjectUtils.isNull(saveOperationRequestDTO.getWeight())) {
            saveOperationRequestDTO.setWeight(0);
        }
        return super.save(
                new Operation(
                        saveOperationRequestDTO.getName(),
                        saveOperationRequestDTO.getCode(),
                        saveOperationRequestDTO.getDescription(),
                        saveOperationRequestDTO.getEditable(),
                        saveOperationRequestDTO.getDeletable(),
                        saveOperationRequestDTO.getWeight(),
                        saveOperationRequestDTO.getMenuId()
                )
        );
    }

    @Transactional
    public OperationResponseDTO update(String id, SaveOperationRequestDTO saveOperationRequestDTO) {
        Menu menu = menuManager.findById(saveOperationRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Operation operation = super.findEntityById(id);
        if (ESObjectUtils.isNull(operation)) {
            throw new BusinessException("操作不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveOperationRequestDTO, operation);
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
        roleMenuOperationManager.deleteAllByOperationId(operation.getId());
        super.deleteById(id);
    }

    public List<OperationResponseDTO> list(ListOperationRequestDTO listOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuId", listOperationRequestDTO.getMenuId()));
        return super.findAll(operationCriteria, listOperationRequestDTO.getSortDataList());
    }

    public PageResponseData<OperationResponseDTO> page(PageOperationRequestDTO pageOperationRequestDTO) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuId", pageOperationRequestDTO.getMenuId()));
        return super.findAll(operationCriteria, pageOperationRequestDTO.getPageRequestData());
    }

    public OperationResponseDTO generate(Operation operation) {
        OperationResponseDTO operationResponseDTO = new OperationResponseDTO();
        ESBeanUtils.copyProperties(operation, operationResponseDTO);
        Menu menu = menuManager.findById(operation.getMenuId());
        operationResponseDTO.setMenuName(ESObjectUtils.isNotNull(menu) ? menu.getName() : null);
        operationResponseDTO.setMenuCode(ESObjectUtils.isNotNull(menu) ? menu.getCode() : null);
        operationResponseDTO.setMenuRoute(ESObjectUtils.isNotNull(menu) ? menu.getRoute() : null);
        return operationResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return null;
    }
}
