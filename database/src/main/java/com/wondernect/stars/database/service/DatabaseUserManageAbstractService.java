package com.wondernect.stars.database.service;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.easyoffice.excel.ESExcelUtils;
import com.wondernect.elements.jdbc.client.config.JDBCClientConfigProperties;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseUserManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseUserManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseUserManage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库用户服务抽象实现类
 *
 * @author liyafei 2020-11-09 15:58:16
 **/
@Service
public abstract class DatabaseUserManageAbstractService extends BaseStringService<DatabaseUserManageResponseDTO, DatabaseUserManage> implements DatabaseUserManageInterface {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Transactional
    @Override
    public DatabaseUserManageResponseDTO create(SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
//TODO:判断对象是否存在
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (ESObjectUtils.isNull(authorizeData)){
            throw new BusinessException("没有登录用户");
        }
        String appId = authorizeData.getAppId();//appId
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
        databaseUserManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseUserManageCriteria.add(Restrictions.eq("databaseManageId", saveDatabaseUserManageRequestDTO.getDatabaseManageId()));
        databaseUserManageCriteria.add(Restrictions.eq("userName", appId + "_" + saveDatabaseUserManageRequestDTO.getUserName()));
        List<DatabaseUserManage> databaseUserManageList = super.findAllEntity(databaseUserManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseUserManageList)) {
            throw new BusinessException("该app已经存在该数据库用户，请重新输入一个用户名称进行创建");
        }
        DatabaseUserManage databaseUserManage = new DatabaseUserManage();
//        ESBeanUtils.copyProperties(saveDatabaseUserManageRequestDTO, databaseUserManage);
        databaseUserManage.setDatabaseManageId(saveDatabaseUserManageRequestDTO.getDatabaseManageId());
        databaseUserManage.setUserName(appId+"_"+saveDatabaseUserManageRequestDTO.getUserName());
        databaseUserManage.setPassword(saveDatabaseUserManageRequestDTO.getPassword());
        databaseUserManage.setRightsState(0);
        databaseUserManage.setRightsMessage("尚未赋予权限");
        return super.save(databaseUserManage);
    }

    @Transactional
    @Override
    public DatabaseUserManageResponseDTO update(String id, SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO) {
        DatabaseUserManage databaseUserManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseUserManage)) {
            throw new BusinessException("数据库用户不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseUserManageRequestDTO, databaseUserManage);
        return super.save(databaseUserManage);
    }

    @Override
    public List<DatabaseUserManageResponseDTO> list(ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO) {
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(databaseUserManageCriteria, listDatabaseUserManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseUserManageResponseDTO> page(PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO) {
        Criteria<DatabaseUserManage> databaseUserManageCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(databaseUserManageCriteria, pageDatabaseUserManageRequestDTO.getPageRequestData());
    }

    @Override
    public List<ESExcelItem> excelItemList() {
        return super.excelItemList(DatabaseUserManageResponseDTO.class);
    }

    @Override
    public void excelDataExport(String exportServiceIdentifier, ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        super.excelDataExport(exportServiceIdentifier, excelItemList(), list(listDatabaseUserManageRequestDTO), "数据库用户信息导出", "数据库用户信息导出", "数据库用户信息导出", request, response);
    }

    @Override
    public DatabaseUserManageResponseDTO generate(DatabaseUserManage databaseUserManage) {
        DatabaseUserManageResponseDTO databaseUserManageResponseDTO = new DatabaseUserManageResponseDTO();
        ESBeanUtils.copyProperties(databaseUserManage, databaseUserManageResponseDTO);
        return databaseUserManageResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        switch (exportServiceIdentifier) {
            default: {
                return new ArrayList<>();
            }
        }
    }
}