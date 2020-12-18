package com.wondernect.stars.database.service.databaseUserRightsShip;

import com.wondernect.stars.database.dto.DatabaseUserRequestDTO;
import com.wondernect.stars.database.dto.DatabaseUserRightsShipResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.TestConnectResponseDTO;

public class DatabaseUserRightsShipContext {

    private DatabaseUserRightsShipInterface databaseUserRightsShipInterface;

    public DatabaseUserRightsShipContext(DatabaseUserRightsShipInterface databaseUserRightsShipInterface) {
        this.databaseUserRightsShipInterface = databaseUserRightsShipInterface;
    }

    public DatabaseUserRightsShipResponseDTO giveRights(int type, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        return databaseUserRightsShipInterface.giveRights(type, saveDatabaseUserRightsShipRequestDTO);
    }

    public void revokeRights(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        databaseUserRightsShipInterface.revokeRights(saveDatabaseUserRightsShipRequestDTO);
    }

    public TestConnectResponseDTO testConnect(DatabaseUserRequestDTO databaseUserRequestDTO) {
        return databaseUserRightsShipInterface.testConnect(databaseUserRequestDTO);
    }

}
