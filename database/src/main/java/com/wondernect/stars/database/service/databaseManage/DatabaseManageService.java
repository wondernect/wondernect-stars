package com.wondernect.stars.database.service.databaseManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseUserManage;
import com.wondernect.stars.database.model.DatabaseUserRightsShip;
import com.wondernect.stars.database.service.databaseUserManage.DatabaseUserManageService;
import com.wondernect.stars.database.service.databaseUserRightsShip.DatabaseUserRightsShipService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 数据库服务
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Service
public class DatabaseManageService extends DatabaseManageAbstractService {

    @Autowired
    private DatabaseUserRightsShipService databaseUserRightsShipService;

    @Autowired
    private DatabaseUserManageService databaseUserManageService;

    @Transactional
    public DatabaseManageResponseDTO initDatabase(String id) {
        DatabaseManage databaseManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要初始化的数据库不存在");
        }
       /* DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要初始化的数据库不存在数据库管理服务");
        }
        JDBCResult jdbcResult = jdbcClient.initDatabase(databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName());
        databaseManage.setInitState(jdbcResult.getResult());
        databaseManage.setInitMessage(jdbcResult.getMessage());*/
        return super.save(databaseManage);
    }

    //查询用户已经有那些数据库的权限
    public List<DatabaseManageResponseDTO> userHasRightsList(String databaseUserId) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(databaseUserId);
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("当前数据库用户不存在");
        }
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", databaseUserId));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = databaseUserRightsShipService.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        Set<String> set = new HashSet<>();
        if (CollectionUtils.isNotEmpty(databaseUserRightsShipList)) {
            for (DatabaseUserRightsShip databaseUserRightsShip : databaseUserRightsShipList) {
                set.add(databaseUserRightsShip.getDatabaseManageId());
            }
        }
        //避免Collections.singletonList(noSet)的集合是个空（这里的空不指null）
        if (set.size() == 0) {
            return super.findAll(new ArrayList<>());
        } else {
            Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
            databaseManageCriteria.add(Restrictions.in("id", Collections.singletonList(set)));
            return super.findAll(databaseManageCriteria, new ArrayList<>());
        }
    }

    //查询用户没有那些数据库的权限
    public List<DatabaseManageResponseDTO> userNoRightsList(String databaseUserId) {
        DatabaseUserManage databaseUserManage = databaseUserManageService.findEntityById(databaseUserId);
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("当前数据库用户不存在");
        }
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", databaseUserManage.getDatabaseRootManageId()));
        List<DatabaseManage> databaseManageList = super.findAllEntity(databaseManageCriteria, new ArrayList<>());
        Criteria<DatabaseUserRightsShip> databaseUserRightsShipCriteria = new Criteria<>();
        databaseUserRightsShipCriteria.add(Restrictions.eq("databaseUserId", databaseUserId));
        List<DatabaseUserRightsShip> databaseUserRightsShipList = databaseUserRightsShipService.findAllEntity(databaseUserRightsShipCriteria, new ArrayList<>());
        Set<String> hasSet = new HashSet<>();//有权限的数据库名称id集合
        Set<String> allSet = new HashSet<>();//所有属于该数据库的数据库名称id集合
        Set<String> noSet = new HashSet<>();//无权限的数据库名称id集合
        if (CollectionUtils.isNotEmpty(databaseUserRightsShipList)) {
            for (DatabaseUserRightsShip databaseUserRightsShip : databaseUserRightsShipList) {
                hasSet.add(databaseUserRightsShip.getDatabaseManageId());
            }
        }
        if (CollectionUtils.isNotEmpty(databaseManageList)) {
            for (DatabaseManage databaseManage : databaseManageList) {
                allSet.add(databaseManage.getId());
            }
        }
        if (CollectionUtils.isNotEmpty(allSet)) {
            for (String s : allSet) {
                if (!hasSet.contains(s)) {
                    noSet.add(s);
                }
            }
        }
        //避免Collections.singletonList(noSet)的集合是个空（这里的空不指null）
        if (noSet.size() == 0) {
            return super.findAll(new ArrayList<>());
        } else {
            Criteria<DatabaseManage> databaseManageCriteria1 = new Criteria<>();
            databaseManageCriteria1.add(Restrictions.in("id", Collections.singletonList(noSet)));
            return super.findAll(databaseManageCriteria1, new ArrayList<>());
        }
    }

}