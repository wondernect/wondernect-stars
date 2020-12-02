package com.wondernect.stars.database.service;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseRootManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseRootManage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库服务抽象实现类
 *
 * @author 李亚飞 2020-12-01 15:00:01
 **/
@Service
public abstract class DatabaseRootManageAbstractService extends BaseStringService<DatabaseRootManageResponseDTO, DatabaseRootManage> implements DatabaseRootManageInterface {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseRootManageCriteria.add(Restrictions.eq("serverIp", saveDatabaseRootManageRequestDTO.getServerIp()));
        List<DatabaseRootManage> databaseRootManageList = super.findAllEntity(databaseRootManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseRootManageList)) {
            throw new BusinessException("该APP已存在地址为" + saveDatabaseRootManageRequestDTO.getServerIp() + "的MySQL数据库管理服务");
        }
        DatabaseRootManage databaseRootManage = new DatabaseRootManage();
        ESBeanUtils.copyProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getServerIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        return super.save(databaseRootManage);
    }

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        DatabaseRootManage databaseRootManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("MySQL数据库不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getServerIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        return super.save(databaseRootManage);
    }

    @Override
    public List<DatabaseRootManageResponseDTO> list(ListDatabaseRootManageRequestDTO listDatabaseRootManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseRootManageCriteria.add(Restrictions.eq("serverIp", listDatabaseRootManageRequestDTO.getServerIp()));
        return super.findAll(databaseRootManageCriteria, listDatabaseRootManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseRootManageResponseDTO> page(PageDatabaseRootManageRequestDTO pageDatabaseRootManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseRootManageCriteria.add(Restrictions.eq("serverIp", pageDatabaseRootManageRequestDTO.getServerIp()));
        return super.findAll(databaseRootManageCriteria, pageDatabaseRootManageRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseRootManageResponseDTO generate(DatabaseRootManage databaseRootManage) {
        DatabaseRootManageResponseDTO databaseRootManageResponseDTO = new DatabaseRootManageResponseDTO();
        ESBeanUtils.copyProperties(databaseRootManage, databaseRootManageResponseDTO);
        return databaseRootManageResponseDTO;
    }
}