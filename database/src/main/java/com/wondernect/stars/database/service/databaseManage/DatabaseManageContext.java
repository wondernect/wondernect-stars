package com.wondernect.stars.database.service.databaseManage;

import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;

public class DatabaseManageContext {

    private DatabaseManageInterface databaseManageInterface;

    public DatabaseManageContext(DatabaseManageInterface databaseManageInterface) {
        this.databaseManageInterface = databaseManageInterface;
    }

    public DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        return databaseManageInterface.create(saveDatabaseManageRequestDTO);
    }

    public DatabaseManageResponseDTO update(String id, SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        return databaseManageInterface.update(id, saveDatabaseManageRequestDTO);
    }

    public DatabaseManageResponseDTO initDatabase(String id) {
        return databaseManageInterface.initDatabase(id);
    }
}
