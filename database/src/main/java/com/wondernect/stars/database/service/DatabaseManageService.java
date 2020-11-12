package com.wondernect.stars.database.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.model.DatabaseManage;
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
    private JDBCClient jdbcClient;

    @Transactional
    public DatabaseManageResponseDTO initDatabase(String id){
        DatabaseManage databaseManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)){
            throw new BusinessException("要初始化的数据库不存在");
        }
        JDBCResult jdbcResult = jdbcClient.initDatabase(databaseManage.getDatabaseName());
        databaseManage.setInitState(jdbcResult.getResult());
        databaseManage.setInitMessage(jdbcResult.getMessage());
        super.save(databaseManage);
        return new DatabaseManageResponseDTO(
                databaseManage.getDatabaseName(),
                databaseManage.getUrl(),
                jdbcResult.getResult(),
                jdbcResult.getMessage()
        );
    }

}