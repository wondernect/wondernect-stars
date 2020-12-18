package com.wondernect.stars.database.service.databaseUserManage;

import com.wondernect.stars.database.dto.DatabaseModifyPasswordRequestDTO;
import com.wondernect.stars.database.dto.DatabaseUserManageResponseDTO;

public class DatabaseUserManageContext {

    private DatabaseUserManageInterface databaseUserManageInterface;

    public DatabaseUserManageContext(DatabaseUserManageInterface databaseUserManageInterface) {
        this.databaseUserManageInterface = databaseUserManageInterface;
    }

    public DatabaseUserManageResponseDTO modifyPassword(DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO) {
        return databaseUserManageInterface.modifyPassword(databaseModifyPasswordRequestDTO);
    }
}
