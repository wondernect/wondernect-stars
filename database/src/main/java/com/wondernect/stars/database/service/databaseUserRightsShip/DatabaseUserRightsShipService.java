package com.wondernect.stars.database.service.databaseUserRightsShip;

import com.wondernect.stars.database.dto.DatabaseUserRequestDTO;
import com.wondernect.stars.database.dto.DatabaseUserRightsShipResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.TestConnectResponseDTO;
import org.springframework.stereotype.Service;

/**
 * 数据库用户权限关系表服务
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Service
public class DatabaseUserRightsShipService extends DatabaseUserRightsShipAbstractService {

    @Override
    public DatabaseUserRightsShipResponseDTO giveRights(int type, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        return null;
    }

    @Override
    public void revokeRights(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {

    }

    @Override
    public TestConnectResponseDTO testConnect(DatabaseUserRequestDTO databaseUserRequestDTO) {
        return null;
    }
}