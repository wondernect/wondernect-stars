package com.wondernect.stars.database.service.databaseRootManage;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseRootManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseRootManageRequestDTO;

import java.util.List;

/**
 * 根数据库服务接口类
 *
 * @author 李亚飞 2020-12-01 15:00:01
 **/
public interface DatabaseRootManageInterface {

    /**
     * 创建
     **/
    DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO);

    /**
     * 更新
     **/
    DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    DatabaseRootManageResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<DatabaseRootManageResponseDTO> list(ListDatabaseRootManageRequestDTO listDatabaseRootManageRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<DatabaseRootManageResponseDTO> page(PageDatabaseRootManageRequestDTO pageDatabaseRootManageRequestDTO);
}