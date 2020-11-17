package com.wondernect.stars.database.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseUserManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserManageRequestDTO;

import java.util.List;

/**
 * 数据库用户服务接口类
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
public interface DatabaseUserManageInterface {

    /**
     * 创建
     **/
    DatabaseUserManageResponseDTO create(SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO);

    /**
     * 更新
     **/
    DatabaseUserManageResponseDTO update(String id, SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    DatabaseUserManageResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<DatabaseUserManageResponseDTO> list(ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<DatabaseUserManageResponseDTO> page(PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO);
}