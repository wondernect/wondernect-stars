package com.wondernect.stars.rbac.service.abs;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
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
import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeService
 * Author: chenxun
 * Date: 2020-02-21 13:52
 * Description:
 */
public abstract class AbstractRoleTypeService implements InitRoleTypeService {

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
        roleType = roleTypeManager.save(new RoleType(saveRoleTypeRequestDTO.getCode(), saveRoleTypeRequestDTO.getName(), saveRoleTypeRequestDTO.getDescription(), saveRoleTypeRequestDTO.getEditable(), saveRoleTypeRequestDTO.getDeletable(), saveRoleTypeRequestDTO.getWeight()));
        return generate(roleType);
    }

    @Transactional
    public RoleTypeResponseDTO update(String id, SaveRoleTypeRequestDTO saveRoleTypeRequestDTO) {
        RoleType roleType = roleTypeManager.findById(id);
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
        roleType = roleTypeManager.save(roleType);
        return generate(roleType);
    }

    @Transactional
    public void delete(String id) {
        RoleType roleType = roleTypeManager.findById(id);
        if (ESObjectUtils.isNull(roleType)) {
            throw new BusinessException("角色类型不存在");
        }
        if (!roleType.getDeletable()) {
            throw new BusinessException("角色类型不可删除");
        }
        roleManager.deleteAllByRoleTypeCode(roleType.getCode());
        roleTypeManager.deleteById(id);
    }

    public RoleTypeResponseDTO getByCode(String code) {
        RoleType roleType = roleTypeManager.findByCode(code);
        if (ESObjectUtils.isNull(roleType)) {
            return null;
        }
        return generate(roleType);
    }

    public RoleTypeResponseDTO getById(String id) {
        RoleType roleType = roleTypeManager.findById(id);
        if (ESObjectUtils.isNull(roleType)) {
            return null;
        }
        return generate(roleType);
    }

    public List<RoleTypeResponseDTO> list(ListRoleTypeRequestDTO listRoleTypeRequestDTO) {
        Criteria<RoleType> roleTypeCriteria = new Criteria<>(LogicalOperator.OR);
        roleTypeCriteria.add(Restrictions.like("code", listRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        roleTypeCriteria.add(Restrictions.like("name", listRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        List<RoleType> roleTypeList = roleTypeManager.findAll(roleTypeCriteria, listRoleTypeRequestDTO.getSortDataList());
        List<RoleTypeResponseDTO> roleTypeResponseDTOList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roleTypeList)) {
            for (RoleType roleType : roleTypeList) {
                roleTypeResponseDTOList.add(generate(roleType));
            }
        }
        return roleTypeResponseDTOList;
    }

    public PageResponseData<RoleTypeResponseDTO> page(PageRoleTypeRequestDTO pageRoleTypeRequestDTO) {
        Criteria<RoleType> roleTypeCriteria = new Criteria<>(LogicalOperator.OR);
        roleTypeCriteria.add(Restrictions.like("code", pageRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        roleTypeCriteria.add(Restrictions.like("name", pageRoleTypeRequestDTO.getValue(), MatchMode.ANYWHERE));
        PageResponseData<RoleType> pageResponseData = roleTypeManager.findAll(roleTypeCriteria, pageRoleTypeRequestDTO.getPageRequestData());
        List<RoleTypeResponseDTO> roleTypeResponseDTOList = new ArrayList<>();
        List<RoleType> roleTypeList = pageResponseData.getDataList();
        if (CollectionUtils.isNotEmpty(roleTypeList)) {
            for (RoleType roleType : roleTypeList) {
                roleTypeResponseDTOList.add(generate(roleType));
            }
        }
        return new PageResponseData<>(
                pageResponseData.getPage(),
                pageResponseData.getSize(),
                pageResponseData.getTotalPages(),
                pageResponseData.getTotalElements(),
                roleTypeResponseDTOList
        );
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
