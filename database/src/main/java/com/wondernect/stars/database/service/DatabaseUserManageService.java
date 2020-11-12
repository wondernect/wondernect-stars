package com.wondernect.stars.database.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.*;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseUserManage;
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

    //赋权限
    @Transactional
    public DatabaseUserManageResponseDTO giveRights(int type, DatabaseUserRequestDTO databaseUserRequestDTO) {
        DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserRequestDTO.getDatabaseManageId());
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要赋权限的数据库不存在");
        }
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("databaseManageId", databaseUserRequestDTO.getDatabaseManageId()));
        databaseUserManageCriteria.add(Restrictions.eq("userName", databaseUserRequestDTO.getDatabaseName()));
        List<DatabaseUserManage> databaseUserManageList = super.findAllEntity(databaseUserManageCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseUserManageList)) {
            throw new BusinessException("要赋权限的数据库用户信息不存在");
        }
        if (databaseUserManageList.get(0).getRightsState() != 0) {
            throw new BusinessException("要赋权限的数据库用户已经有权限了");
        }
        DatabaseUserManage databaseUserManage = databaseUserManageList.get(0);
        jdbcClient.giveRights(type, databaseUserRequestDTO.getDatabaseName(), databaseUserRequestDTO.getUserName(), databaseUserRequestDTO.getPassword());
        databaseUserManage.setRightsState(type);
        if (type == 1) {
            databaseUserManage.setRightsMessage("只读权限");
        } else {
            databaseUserManage.setRightsMessage("所有权限");
        }
        super.save(databaseUserManage);
        return new DatabaseUserManageResponseDTO(
                databaseUserManage.getId(),
                databaseUserManage.getDatabaseManageId(),
                databaseUserManage.getUserName(),
                databaseUserManage.getPassword(),
                databaseUserManage.getRightsState(),
                databaseUserManage.getRightsMessage()
        );
    }

    //收回权限
    @Transactional
    public DatabaseUserManageResponseDTO revokeRights(DatabaseUserRequestDTO databaseUserRequestDTO) {
        DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserRequestDTO.getDatabaseManageId());
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要收回权限的数据库不存在");
        }
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("databaseManageId", databaseUserRequestDTO.getDatabaseManageId()));
        databaseUserManageCriteria.add(Restrictions.eq("userName", databaseUserRequestDTO.getDatabaseName()));
        List<DatabaseUserManage> databaseUserManageList = super.findAllEntity(databaseUserManageCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseUserManageList)) {
            throw new BusinessException("要收回权限的数据库用户信息不存在");
        }
        if (databaseUserManageList.get(0).getRightsState() == 0) {
            throw new BusinessException("要收回权限的数据库用户没有需要收回的权限");
        }
        DatabaseUserManage databaseUserManage = databaseUserManageList.get(0);
        JDBCResult jdbcResult = jdbcClient.revokeRights(databaseUserRequestDTO.getDatabaseName(), databaseUserRequestDTO.getUserName());
        if (jdbcResult.getResult()) {
            databaseUserManage.setRightsState(0);
            databaseUserManage.setRightsMessage("尚未赋予权限");
            super.save(databaseUserManage);
        }
        return new DatabaseUserManageResponseDTO(
                databaseUserManage.getId(),
                databaseUserManage.getDatabaseManageId(),
                databaseUserManage.getUserName(),
                databaseUserManage.getPassword(),
                databaseUserManage.getRightsState(),
                jdbcResult.getMessage()
        );
    }

    //测试连接
    public TestConnectResponseDTO testConnect(DatabaseConnectRequestDTO databaseConnectRequestDTO) {
        JDBCResult jdbcResult = jdbcClient.testConnect(databaseConnectRequestDTO.getUrl(), databaseConnectRequestDTO.getUserName(), databaseConnectRequestDTO.getPassword());
        return new TestConnectResponseDTO(
                databaseConnectRequestDTO.getUrl(),
                databaseConnectRequestDTO.getUserName(),
                databaseConnectRequestDTO.getPassword(),
                jdbcResult.getResult(),
                jdbcResult.getMessage()
        );
    }

    //修改密码
    @Transactional
    public DatabaseUserManageResponseDTO modifyPassword(DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO) {
        DatabaseUserManage databaseUserManage = super.findEntityById(databaseModifyPasswordRequestDTO.getId());
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("需要修改密码的用户不存在");
        }
        if (databaseUserManage.getRightsState() == 0) {
            throw new BusinessException("该用户无数据库操作权限，无法为其修改密码");
        }
        DatabaseManage databaseManage = databaseManageService.findEntityById(databaseUserManage.getDatabaseManageId());
        jdbcClient.giveRights(databaseUserManage.getRightsState(), databaseManage.getDatabaseName(), databaseUserManage.getUserName(), databaseModifyPasswordRequestDTO.getNewPassword());
        databaseUserManage.setPassword(databaseModifyPasswordRequestDTO.getNewPassword());
        DatabaseUserManageResponseDTO databaseUserManageResponseDTO = super.save(databaseUserManage);
        return new DatabaseUserManageResponseDTO(
                databaseUserManageResponseDTO.getId(),
                databaseUserManageResponseDTO.getDatabaseManageId(),
                databaseUserManageResponseDTO.getUserName(),
                databaseUserManageResponseDTO.getPassword(),
                databaseUserManageResponseDTO.getRightsState(),
                databaseUserManageResponseDTO.getRightsMessage()
        );
    }
}