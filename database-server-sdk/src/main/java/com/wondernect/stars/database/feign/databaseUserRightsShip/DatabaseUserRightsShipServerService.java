package com.wondernect.stars.database.feign.databaseUserRightsShip;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseUserRightsShipServerService {

    @Autowired
    private DatabaseUserRightsShipFeignClient databaseUserRightsShipFeignClient;

    public DatabaseUserRightsShipResponseDTO detail(String id) {
        BusinessData<DatabaseUserRightsShipResponseDTO> businessData = databaseUserRightsShipFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<DatabaseUserRightsShipResponseDTO> list(ListDatabaseUserRightsShipRequestDTO listDatabaseUserRightsShipRequestDTO) {
        BusinessData<List<DatabaseUserRightsShipResponseDTO>> businessData = databaseUserRightsShipFeignClient.list(listDatabaseUserRightsShipRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<DatabaseUserRightsShipResponseDTO> page(PageDatabaseUserRightsShipRequestDTO pageDatabaseUserRightsShipRequestDTO) {
        BusinessData<PageResponseData<DatabaseUserRightsShipResponseDTO>> businessData = databaseUserRightsShipFeignClient.page(pageDatabaseUserRightsShipRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public DatabaseUserRightsShipResponseDTO giveRights(Integer type, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        BusinessData<DatabaseUserRightsShipResponseDTO> businessData = databaseUserRightsShipFeignClient.giveRights(type, saveDatabaseUserRightsShipRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean revokeRights(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO) {
        BusinessData businessData = databaseUserRightsShipFeignClient.revokeRights(saveDatabaseUserRightsShipRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public TestConnectResponseDTO testConnect(DatabaseUserRequestDTO databaseUserRequestDTO) {
        BusinessData<TestConnectResponseDTO> businessData = databaseUserRightsShipFeignClient.testConnect(databaseUserRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
