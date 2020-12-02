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
import com.wondernect.stars.database.dto.DatabaseUserRightsShipResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseUserManage;
import com.wondernect.stars.database.model.DatabaseUserRightsShip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 数据库用户权限关系表服务抽象实现类
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Service
public abstract class DatabaseUserRightsShipAbstractService extends BaseStringService<DatabaseUserRightsShipResponseDTO, DatabaseUserRightsShip> implements DatabaseUserRightsShipInterface {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private DatabaseUserManageService databaseUserManageService;

    @Autowired
    private DatabaseManageService databaseManageService;

    @Transactional
    @Override
    public DatabaseUserRightsShipResponseDTO create(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
//TODO:判断对象是否存在

        DatabaseUserRightsShip databaseUserRightsShip = new DatabaseUserRightsShip();
        ESBeanUtils.copyProperties(saveDatabaseUserRightsShipRequestDTO, databaseUserRightsShip);
        return super.save(databaseUserRightsShip);
    }

    @Transactional
    @Override
    public DatabaseUserRightsShipResponseDTO update(String id, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        DatabaseUserRightsShip databaseUserRightsShip = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseUserRightsShip)) {
            throw new BusinessException("数据库用户权限关系表不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseUserRightsShipRequestDTO, databaseUserRightsShip);
        return super.save(databaseUserRightsShip);
    }

    @Override
    public List<DatabaseUserRightsShipResponseDTO> list(ListDatabaseUserRightsShipRequestDTO listDatabaseUserRightsShipRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", listDatabaseUserRightsShipRequestDTO.getDatabaseUserId()));
        return super.findAll(databaseUserRightsShipCriteria, listDatabaseUserRightsShipRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseUserRightsShipResponseDTO> page(PageDatabaseUserRightsShipRequestDTO pageDatabaseUserRightsShipRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", pageDatabaseUserRightsShipRequestDTO.getDatabaseUserId()));
        return super.findAll(databaseUserRightsShipCriteria, pageDatabaseUserRightsShipRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseUserRightsShipResponseDTO generate(DatabaseUserRightsShip databaseUserRightsShip) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(databaseUserRightsShip.getDatabaseUserId());
        DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserRightsShip.getDatabaseManageId());
        DatabaseUserRightsShipResponseDTO databaseUserRightsShipResponseDTO = new DatabaseUserRightsShipResponseDTO();
        ESBeanUtils.copyProperties(databaseUserRightsShip, databaseUserRightsShipResponseDTO);
        databaseUserRightsShipResponseDTO.setDatabaseName(ESObjectUtils.isNotNull(databaseManage) ? databaseManage.getDatabaseName() : null);
        databaseUserRightsShipResponseDTO.setUserName(ESObjectUtils.isNotNull(databaseUserManage) ? databaseUserManage.getUserName() : null);
        return databaseUserRightsShipResponseDTO;
    }
}