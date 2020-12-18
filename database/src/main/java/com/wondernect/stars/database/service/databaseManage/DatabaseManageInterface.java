package com.wondernect.stars.database.service.databaseManage;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;

import java.util.List;

/**
 * 数据库服务接口类
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
public interface DatabaseManageInterface {

    /**
     * 创建
     **/
    DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO);

    /**
     * 更新
     **/
    DatabaseManageResponseDTO update(String id, SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    DatabaseManageResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<DatabaseManageResponseDTO> list(ListDatabaseManageRequestDTO listDatabaseManageRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<DatabaseManageResponseDTO> page(PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO);

    /**
     * 初始化数据库
     */
    DatabaseManageResponseDTO initDatabase(String id);
}