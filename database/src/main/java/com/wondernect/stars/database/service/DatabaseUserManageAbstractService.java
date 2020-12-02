package com.wondernect.stars.database.service;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseUserManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseUserManage;
import com.wondernect.stars.database.model.DatabaseUserRightsShip;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实例用户服务抽象实现类
 *
 * @author liyafei 2020-11-09 15:58:16
 **/
@Service
public abstract class DatabaseUserManageAbstractService extends BaseStringService<DatabaseUserManageResponseDTO, DatabaseUserManage> implements DatabaseUserManageInterface {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private DatabaseUserRightsShipService databaseUserRightsShipService;

    @Transactional
    @Override
    public DatabaseUserManageResponseDTO create(SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserManageCriteria.add(Restrictions.eq("databaseRootManageId", saveDatabaseUserManageRequestDTO.getDatabaseRootManageId()));
        databaseUserManageCriteria.add(Restrictions.eq("userName", saveDatabaseUserManageRequestDTO.getUserName()));
        List<DatabaseUserManage> databaseUserManageList = super.findAllEntity(databaseUserManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseUserManageList)) {
            throw new BusinessException("该MySQL数据库已存在该用户，请重新输入一个用户名称进行创建");
        }
        DatabaseUserManage databaseUserManage = new DatabaseUserManage();
        databaseUserManage.setDatabaseRootManageId(saveDatabaseUserManageRequestDTO.getDatabaseRootManageId());
        databaseUserManage.setUserName(saveDatabaseUserManageRequestDTO.getUserName());
        databaseUserManage.setPassword(saveDatabaseUserManageRequestDTO.getPassword());
        return super.save(databaseUserManage);
    }

    @Transactional
    @Override
    public DatabaseUserManageResponseDTO update(String id, SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
        DatabaseUserManage databaseUserManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("数据库用户不存在");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", id));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = databaseUserRightsShipService.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseUserRightsShipList)) {
            throw new BusinessException("该用户已被赋予权限，不允许修改");
        }
        databaseUserManage.setUserName(saveDatabaseUserManageRequestDTO.getUserName());
        databaseUserManage.setPassword(saveDatabaseUserManageRequestDTO.getPassword());
        return super.save(databaseUserManage);
    }

    @Override
    public List<DatabaseUserManageResponseDTO> list(ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserManageCriteria.add(Restrictions.eq("databaseRootManageId", listDatabaseUserManageRequestDTO.getDatabaseRootManageId()));
        return super.findAll(databaseUserManageCriteria, listDatabaseUserManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseUserManageResponseDTO> page(PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserManageCriteria.add(Restrictions.eq("databaseRootManageId", pageDatabaseUserManageRequestDTO.getDatabaseRootManageId()));
        return super.findAll(databaseUserManageCriteria, pageDatabaseUserManageRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseUserManageResponseDTO generate(DatabaseUserManage databaseUserManage) {
        DatabaseUserManageResponseDTO databaseUserManageResponseDTO = new DatabaseUserManageResponseDTO();
        ESBeanUtils.copyProperties(databaseUserManage, databaseUserManageResponseDTO);
        return databaseUserManageResponseDTO;
    }
}