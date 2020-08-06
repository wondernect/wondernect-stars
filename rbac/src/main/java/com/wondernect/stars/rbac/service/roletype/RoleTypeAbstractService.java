package com.wondernect.stars.rbac.service.roletype;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeService
 * Author: chenxun
 * Date: 2020-02-21 13:52
 * Description:
 */
public abstract class RoleTypeAbstractService extends BaseStringService<RoleTypeResponseDTO, RoleType> implements RoleTypeInterface {

    @Autowired
    private RoleManager roleManager;

    @Transactional
    public RoleTypeResponseDTO create(SaveRoleTypeRequestDTO saveRoleTypeRequestDTO) {
        if (ESObjectUtils.isNull(saveRoleTypeRequestDTO.getWeight())) {
            saveRoleTypeRequestDTO.setWeight(0);
        }
        return super.save(
                new RoleType(
                        saveRoleTypeRequestDTO.getName(),
                        saveRoleTypeRequestDTO.getDescription(),
                        saveRoleTypeRequestDTO.getEditable(),
                        saveRoleTypeRequestDTO.getDeletable(),
                        saveRoleTypeRequestDTO.getWeight()
                )
        );
    }

    @Transactional
    public RoleTypeResponseDTO update(String id, SaveRoleTypeRequestDTO saveRoleTypeRequestDTO) {
        RoleType roleType = super.findEntityById(id);
        if (ESObjectUtils.isNull(roleType)) {
            throw new BusinessException("角色类型不存在");
        }
        if (!roleType.getEditable()) {
            throw new BusinessException("角色类型不可编辑");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveRoleTypeRequestDTO, roleType);
        return super.save(roleType);
    }

    @Transactional
    public void deleteById(String id) {
        RoleType roleType = super.findEntityById(id);
        if (ESObjectUtils.isNull(roleType)) {
            throw new BusinessException("角色类型不存在");
        }
        if (!roleType.getDeletable()) {
            throw new BusinessException("角色类型不可删除");
        }
        roleManager.deleteAllByRoleTypeId(roleType.getId());
        super.deleteById(id);
    }

    public List<RoleTypeResponseDTO> list(ListRoleTypeRequestDTO listRoleTypeRequestDTO) {
        return super.findAll(listRoleTypeRequestDTO.getSortDataList());
    }

    public PageResponseData<RoleTypeResponseDTO> page(PageRoleTypeRequestDTO pageRoleTypeRequestDTO) {
        return super.findAll(pageRoleTypeRequestDTO.getPageRequestData());
    }

    public RoleTypeResponseDTO generate(RoleType roleType) {
        RoleTypeResponseDTO roleTypeResponseDTO = new RoleTypeResponseDTO();
        ESBeanUtils.copyProperties(roleType, roleTypeResponseDTO);
        roleTypeResponseDTO.setId(roleType.getId());
        return roleTypeResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return null;
    }
}
