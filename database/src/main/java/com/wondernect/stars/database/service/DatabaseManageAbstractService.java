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
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库实例服务抽象实现类
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Service
public abstract class DatabaseManageAbstractService extends BaseStringService<DatabaseManageResponseDTO, DatabaseManage> implements DatabaseManageInterface {

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Transactional
    @Override
    public DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("createApp", appId));
        List<DatabaseRootManage> databaseRootManageList = databaseRootManageService.findAllEntity(databaseRootManageCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(databaseRootManageList)) {
            throw new BusinessException("该APP无对应的MySQL数据库管理服务");
        }
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", saveDatabaseManageRequestDTO.getDatabaseRootManageId()));
        databaseManageCriteria.add(Restrictions.eq("databaseName", saveDatabaseManageRequestDTO.getDatabaseName()));
        List<DatabaseManage> databaseManageList = super.findAllEntity(databaseManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseManageList)) {
            throw new BusinessException("该MySQL数据库已存在名为" + saveDatabaseManageRequestDTO.getDatabaseName() + "的数据库，请重新输入一个数据库名称进行创建");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(saveDatabaseManageRequestDTO.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("您选择的MySQL数据库不存在");
        }
        String serverIp = databaseRootManage.getServerIp();
        String port = String.valueOf(databaseRootManage.getPort());
        DatabaseManage databaseManage = new DatabaseManage();
        databaseManage.setDatabaseRootManageId(databaseRootManage.getId());
        databaseManage.setDatabaseName(saveDatabaseManageRequestDTO.getDatabaseName());
        databaseManage.setUrl("jdbc:mysql://" + serverIp + ":" + port + "/" + saveDatabaseManageRequestDTO.getDatabaseName() + "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        databaseManage.setInitState(false);
        databaseManage.setInitMessage("数据库尚未初始化");
        return super.save(databaseManage);
    }

    @Transactional
    @Override
    public DatabaseManageResponseDTO update(String id, SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        DatabaseManage databaseManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("数据库不存在");
        }
        if (databaseManage.getInitState()) {
            throw new BusinessException("数据库已初始化，不允许修改");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(saveDatabaseManageRequestDTO.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("您选择的MySQL数据库不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseManageRequestDTO, databaseManage);
        return super.save(databaseManage);
    }

    @Override
    public List<DatabaseManageResponseDTO> list(ListDatabaseManageRequestDTO listDatabaseManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", listDatabaseManageRequestDTO.getDatabaseRootManageId()));
        databaseManageCriteria.add(Restrictions.eq("databaseName", listDatabaseManageRequestDTO.getDatabaseName()));
        return super.findAll(databaseManageCriteria, listDatabaseManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseManageResponseDTO> page(PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO) {
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (authorizeData.getUserId() == null) {
            throw new BusinessException("当前无登录用户");
        }
        String appId = authorizeData.getAppId();
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", pageDatabaseManageRequestDTO.getDatabaseRootManageId()));
        databaseManageCriteria.add(Restrictions.eq("databaseName", pageDatabaseManageRequestDTO.getDatabaseName()));
        return super.findAll(databaseManageCriteria, pageDatabaseManageRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseManageResponseDTO generate(DatabaseManage databaseManage) {
        DatabaseManageResponseDTO databaseManageResponseDTO = new DatabaseManageResponseDTO();
        ESBeanUtils.copyProperties(databaseManage, databaseManageResponseDTO);
        return databaseManageResponseDTO;
    }
}