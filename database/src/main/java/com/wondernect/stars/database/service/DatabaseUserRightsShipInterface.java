package com.wondernect.stars.database.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseUserRightsShipResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseUserRightsShipRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserRightsShipRequestDTO;

import java.util.List;

/**
 * 数据库用户权限关系表服务接口类
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
public interface DatabaseUserRightsShipInterface {

    /**
     * 创建
     **/
    DatabaseUserRightsShipResponseDTO create(SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO);

    /**
     * 更新
     **/
    DatabaseUserRightsShipResponseDTO update(String id, SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    DatabaseUserRightsShipResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<DatabaseUserRightsShipResponseDTO> list(ListDatabaseUserRightsShipRequestDTO listDatabaseUserRightsShipRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<DatabaseUserRightsShipResponseDTO> page(PageDatabaseUserRightsShipRequestDTO pageDatabaseUserRightsShipRequestDTO);
}