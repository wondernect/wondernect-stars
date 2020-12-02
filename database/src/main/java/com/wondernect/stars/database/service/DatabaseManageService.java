package com.wondernect.stars.database.service;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 数据库服务
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Service
public class DatabaseManageService extends DatabaseManageAbstractService {

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private JDBCClient jdbcClient;

    @Transactional
    public DatabaseManageResponseDTO initDatabase(String id) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        DatabaseManage databaseManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要初始化的数据库不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要初始化的数据库不存在MySQL数据库管理服务");
        }
        JDBCResult jdbcResult = jdbcClient.initDatabase(databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName());
        databaseManage.setInitState(jdbcResult.getResult());
        databaseManage.setInitMessage(jdbcResult.getMessage());
        return super.save(databaseManage);
    }

}