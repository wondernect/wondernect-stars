package com.wondernect.stars.database.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.DatabaseModifyPasswordRequestDTO;
import com.wondernect.stars.database.dto.DatabaseUserManageResponseDTO;
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
 * 数据库用户服务
 *
 * @author liyafei 2020-11-09 15:58:16
 **/
@Service
public class DatabaseUserManageService extends DatabaseUserManageAbstractService {

    @Autowired
    private JDBCClient jdbcClient;

    @Autowired
    private DatabaseManageService databaseManageService;

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    @Autowired
    private DatabaseUserRightsShipService databaseUserRightsShipService;

    //修改密码
    @Transactional
    public DatabaseUserManageResponseDTO modifyPassword(DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO) {
        DatabaseUserManage databaseUserManage = super.findEntityById(databaseModifyPasswordRequestDTO.getDatabaseUserId());
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("需要修改密码的用户不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseUserManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要修改密码的用户不存在数据库管理服务");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", databaseModifyPasswordRequestDTO.getDatabaseUserId()));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = databaseUserRightsShipService.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseUserRightsShipList)) {
            //如果没有权限关系，就直接修改返回
            databaseUserManage.setPassword(databaseModifyPasswordRequestDTO.getNewPassword());
            return super.save(databaseUserManage);
        }
        for (DatabaseUserRightsShip databaseUserRightsShip : databaseUserRightsShipList) {
            DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserRightsShip.getDatabaseManageId());
            jdbcClient.giveRights(databaseUserRightsShip.getRightsState(), databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName(), databaseUserManage.getUsername(), databaseModifyPasswordRequestDTO.getNewPassword());
        }
        databaseUserManage.setPassword(databaseModifyPasswordRequestDTO.getNewPassword());
        return super.save(databaseUserManage);
    }
}