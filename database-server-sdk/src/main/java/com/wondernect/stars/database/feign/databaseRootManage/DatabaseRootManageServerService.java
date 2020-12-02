package com.wondernect.stars.database.feign.databaseRootManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseRootManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseRootManageRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseRootManageServerService {

    @Autowired
    private DatabaseRootManageFeignClient databaseRootManageFeignClient;

    public DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        BusinessData<DatabaseRootManageResponseDTO> businessData = databaseRootManageFeignClient.create(saveDatabaseRootManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        BusinessData<DatabaseRootManageResponseDTO> businessData = databaseRootManageFeignClient.update(id, saveDatabaseRootManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = databaseRootManageFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public DatabaseRootManageResponseDTO detail(String id) {
        BusinessData<DatabaseRootManageResponseDTO> businessData = databaseRootManageFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseRootManageResponseDTO> list(ListDatabaseRootManageRequestDTO listDatabaseRootManageRequestDTO) {
        BusinessData<List<DatabaseRootManageResponseDTO>> businessData = databaseRootManageFeignClient.list(listDatabaseRootManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<DatabaseRootManageResponseDTO> page(PageDatabaseRootManageRequestDTO pageDatabaseRootManageRequestDTO) {
        BusinessData<PageResponseData<DatabaseRootManageResponseDTO>> businessData = databaseRootManageFeignClient.page(pageDatabaseRootManageRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
