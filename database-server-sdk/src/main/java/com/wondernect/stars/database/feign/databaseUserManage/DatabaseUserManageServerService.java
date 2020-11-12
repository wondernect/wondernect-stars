package com.wondernect.stars.database.feign.databaseUserManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseUserManageServerService {

    @Autowired
    private DatabaseUserManageFeignClient databaseUserManageFeignClient;

    public DatabaseUserManageResponseDTO create(SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.create(saveDatabaseUserManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseUserManageResponseDTO update(String id, SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.update(id, saveDatabaseUserManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = databaseUserManageFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public DatabaseUserManageResponseDTO detail(String id) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseUserManageResponseDTO> list(ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO) {
        BusinessData<List<DatabaseUserManageResponseDTO>> businessData = databaseUserManageFeignClient.list(listDatabaseUserManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<DatabaseUserManageResponseDTO> page(PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO) {
        BusinessData<PageResponseData<DatabaseUserManageResponseDTO>> businessData = databaseUserManageFeignClient.page(pageDatabaseUserManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseUserManageResponseDTO giveRights(int type, DatabaseUserRequestDTO databaseUserRequestDTO) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.giveRights(type, databaseUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseUserManageResponseDTO revokeRights(DatabaseUserRequestDTO databaseUserRequestDTO) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.revokeRights(databaseUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public TestConnectResponseDTO testConnect(DatabaseConnectRequestDTO databaseConnectRequestDTO) {
        BusinessData<TestConnectResponseDTO> businessData = databaseUserManageFeignClient.testConnect(databaseConnectRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseUserManageResponseDTO modifyPassword(DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO) {
        BusinessData<DatabaseUserManageResponseDTO> businessData = databaseUserManageFeignClient.modifyPassword(databaseModifyPasswordRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
