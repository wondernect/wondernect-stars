package com.wondernect.stars.rbac.service.astract;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.RoleTypeManager;
import com.wondernect.stars.rbac.model.RoleType;
import com.wondernect.stars.rbac.service.InitRoleTypeService;
import org.hibernate.criterion.MatchMode;
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
public abstract class AbstractRoleTypeService extends BaseStringService<RoleTypeResponseDTO, RoleType> implements InitRoleTypeService {

    @Autowired
    private RoleTypeManager roleTypeManager;

    @Autowired
    private RoleManager roleManager;

    @Transactional
    public RoleTypeResponseDTO create(SaveRoleTypeRequestDTO saveRoleTypeRequestDTO) {
        RoleType roleType = roleTypeManager.findByCode(saveRoleTypeRequestDTO.getCode());
        if (ESObjectUtils.isNotNull(roleType)) {
            throw new BusinessException("角色类型已存在");
        }
        return super.save(
                new RoleType(
                        saveRoleTypeRequestDTO.getCode(),
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
        roleType.setName(saveRoleTypeRequestDTO.getName());
        roleType.setDescription(saveRoleTypeRequestDTO.getDescription());
        roleType.setEditable(saveRoleTypeRequestDTO.getEditable());
        roleType.setDeletable(saveRoleTypeRequestDTO.getDeletable());
        if (ESObjectUtils.isNotNull(saveRoleTypeRequestDTO.getWeight())) {
            roleType.setWeight(saveRoleTypeRequestDTO.getWeight());
        }
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
        roleManager.deleteAllByRoleTypeCode(roleType.getCode());
        super.deleteById(id);
    }

    public RoleTypeResponseDTO findByCode(String code) {
        RoleType roleType = roleTypeManager.findByCode(code);
        if (ESObjectUtils.isNull(roleType)) {
            return null;
        }
        return generate(roleType);
    }

    public List<RoleTypeResponseDTO> list(ListRoleTypeRequestDTO listRoleTypeRequestDTO) {
        Criteria<RoleType> roleTypeCriteria = new Criteria<>(LogicalOperator.OR);
        roleTypeCriteria.add(Restrictions.like("code", listRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        roleTypeCriteria.add(Restrictions.like("name", listRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(roleTypeCriteria, listRoleTypeRequestDTO.getSortDataList());
    }

    public PageResponseData<RoleTypeResponseDTO> page(PageRoleTypeRequestDTO pageRoleTypeRequestDTO) {
        Criteria<RoleType> roleTypeCriteria = new Criteria<>(LogicalOperator.OR);
        roleTypeCriteria.add(Restrictions.like("code", pageRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        roleTypeCriteria.add(Restrictions.like("name", pageRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(roleTypeCriteria, pageRoleTypeRequestDTO.getPageRequestData());
    }

    public RoleTypeResponseDTO generate(RoleType roleType) {
        return new RoleTypeResponseDTO(
                roleType.getId(),
                roleType.getCode(),
                roleType.getName(),
                roleType.getDescription(),
                roleType.getEditable(),
                roleType.getDeletable(),
                roleType.getWeight()
        );
    }
}
