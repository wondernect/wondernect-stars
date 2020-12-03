package com.wondernect.stars.database.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.DatabaseUserRequestDTO;
import com.wondernect.stars.database.dto.DatabaseUserRightsShipResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.TestConnectResponseDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import com.wondernect.stars.database.model.DatabaseUserManage;
import com.wondernect.stars.database.model.DatabaseUserRightsShip;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库用户权限关系表服务
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Service
public class DatabaseUserRightsShipService extends DatabaseUserRightsShipAbstractService {

    @Autowired
    private JDBCClient jdbcClient;

    @Autowired
    private DatabaseUserManageService databaseUserManageService;

    @Autowired
    private DatabaseManageService databaseManageService;

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    //赋权限
    @Transactional
    public DatabaseUserRightsShipResponseDTO giveRights(int type, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(saveDatabaseUserRightsShipRequestDTO.getDatabaseUserId());
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("要赋权限的用户信息不存在");
        }
        DatabaseManage databaseManage = databaseManageService.findEntityById(saveDatabaseUserRightsShipRequestDTO.getDatabaseManageId());
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要赋权限的数据库不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要赋权限的数据库不存在数据库管理服务");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseManageId", saveDatabaseUserRightsShipRequestDTO.getDatabaseManageId()));
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", saveDatabaseUserRightsShipRequestDTO.getDatabaseUserId()));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = super.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseUserRightsShipList)) {
            throw new BusinessException("要赋权限的用户和数据库已经存在权限关系");
        }
        JDBCResult jdbcResult = jdbcClient.giveRights(type, databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName(), databaseUserManage.getUsername(), databaseUserManage.getPassword());
        DatabaseUserRightsShip databaseUserRightsShip = new DatabaseUserRightsShip();
        if (jdbcResult.getResult()) {
            databaseUserRightsShip.setDatabaseManageId(saveDatabaseUserRightsShipRequestDTO.getDatabaseManageId());
            databaseUserRightsShip.setDatabaseUserId(saveDatabaseUserRightsShipRequestDTO.getDatabaseUserId());
            databaseUserRightsShip.setRightsState(type);
            if (type == 1) {
                databaseUserRightsShip.setRightsMessage("只读权限");
            } else {
                databaseUserRightsShip.setRightsMessage("所有权限");
            }
            super.save(databaseUserRightsShip);
        }
        return new DatabaseUserRightsShipResponseDTO(
                databaseUserRightsShip.getDatabaseManageId(),
                databaseManage.getDatabaseName(),
                databaseUserRightsShip.getDatabaseUserId(),
                databaseUserManage.getUsername(),
                databaseUserRightsShip.getRightsState(),
                databaseUserRightsShip.getRightsMessage()
        );
    }

    //收回权限
    @Transactional
    public void revokeRights(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(saveDatabaseUserRightsShipRequestDTO.getDatabaseUserId());
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("要收回权限的用户信息不存在");
        }
        DatabaseManage databaseManage = databaseManageService.findEntityById(saveDatabaseUserRightsShipRequestDTO.getDatabaseManageId());
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要收回权限的数据库不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要收回权限的数据库不存在数据库管理服务");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseManageId", saveDatabaseUserRightsShipRequestDTO.getDatabaseManageId()));
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", saveDatabaseUserRightsShipRequestDTO.getDatabaseUserId()));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = super.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseUserRightsShipList)) {
            throw new BusinessException("要收回的数据库和用户权限关系不存在");
        }
        JDBCResult jdbcResult = jdbcClient.revokeRights(databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName(), databaseUserManage.getUsername());
        if (jdbcResult.getResult()) {
            for (DatabaseUserRightsShip databaseUserRightsShip : databaseUserRightsShipList) {//目前集合中就只有一个对象
                super.deleteById(databaseUserRightsShip.getId());
            }
        }
    }

    //测试连接
    public TestConnectResponseDTO testConnect(DatabaseUserRequestDTO databaseUserRequestDTO) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(databaseUserRequestDTO.getDatabaseUserId());
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("要测试连接的用户信息不存在");
        }
        DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserRequestDTO.getDatabaseManageId());
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要测试连接的数据库不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要测试连接的数据库不存在数据库管理服务");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseManageId", databaseUserRequestDTO.getDatabaseManageId()));
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", databaseUserRequestDTO.getDatabaseUserId()));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = super.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseUserRightsShipList)) {
            throw new BusinessException("要测试连接的用户没有该数据库的操作权限，请先赋予相应的权限");
        }
        JDBCResult jdbcResult = jdbcClient.testConnect(databaseRootManage.getDriver(), databaseManage.getUrl(), databaseUserManage.getUsername(), databaseUserManage.getPassword());
        return new TestConnectResponseDTO(
                databaseManage.getUrl(),
                databaseUserManage.getUsername(),
                databaseUserManage.getPassword(),
                jdbcResult.getResult(),
                jdbcResult.getMessage()
        );
    }

}