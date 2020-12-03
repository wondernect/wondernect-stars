package com.wondernect.stars.database.feign.databaseManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseManageServerService {

    @Autowired
    private DatabaseManageFeignClient databaseManageFeignClient;

    public DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        BusinessData<DatabaseManageResponseDTO> businessData = databaseManageFeignClient.create(saveDatabaseManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseManageResponseDTO update(String id, SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        BusinessData<DatabaseManageResponseDTO> businessData = databaseManageFeignClient.update(id, saveDatabaseManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = databaseManageFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public DatabaseManageResponseDTO detail(String id) {
        BusinessData<DatabaseManageResponseDTO> businessData = databaseManageFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseManageResponseDTO> list(ListDatabaseManageRequestDTO listDatabaseManageRequestDTO) {
        BusinessData<List<DatabaseManageResponseDTO>> businessData = databaseManageFeignClient.list(listDatabaseManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<DatabaseManageResponseDTO> page(PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO) {
        BusinessData<PageResponseData<DatabaseManageResponseDTO>> businessData = databaseManageFeignClient.page(pageDatabaseManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseManageResponseDTO initDatabase(String id) {
        BusinessData<DatabaseManageResponseDTO> businessData = databaseManageFeignClient.initDatabase(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseManageResponseDTO> userHasRightsList(String databaseUserId) {
        BusinessData<List<DatabaseManageResponseDTO>> businessData = databaseManageFeignClient.userHasRightsList(databaseUserId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseManageResponseDTO> userNoRightsList(String databaseUserId) {
        BusinessData<List<DatabaseManageResponseDTO>> businessData = databaseManageFeignClient.userNoRightsList(databaseUserId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
