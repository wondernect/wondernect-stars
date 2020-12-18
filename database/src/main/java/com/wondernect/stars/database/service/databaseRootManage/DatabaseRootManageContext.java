package com.wondernect.stars.database.service.databaseRootManage;

import com.wondernect.stars.database.dto.DatabaseRootManageResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseRootManageRequestDTO;

public class DatabaseRootManageContext {

    private DatabaseRootManageInterface databaseRootManageInterface;

    public DatabaseRootManageContext(DatabaseRootManageInterface databaseRootManageInterface) {
        this.databaseRootManageInterface = databaseRootManageInterface;
    }

    public DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        return databaseRootManageInterface.create(saveDatabaseRootManageRequestDTO);
    }

    public DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        return databaseRootManageInterface.update(id, saveDatabaseRootManageRequestDTO);
    }
}
