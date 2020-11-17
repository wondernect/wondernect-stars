package com.wondernect.stars.database.service;

import com.wondernect.elements.authorize.context.AuthorizeData;
import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.jdbc.client.config.JDBCClientConfigProperties;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库服务抽象实现类
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Service
public abstract class DatabaseManageAbstractService extends BaseStringService<DatabaseManageResponseDTO, DatabaseManage> implements DatabaseManageInterface {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private JDBCClientConfigProperties jdbcClientConfigProperties;

    @Transactional
    @Override
    public DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
//TODO:判断对象是否存在
        AuthorizeData authorizeData = wondernectCommonContext.getAuthorizeData();
        if (ESStringUtils.isRealEmpty(authorizeData.getUserId())) {
            throw new BusinessException("没有登录用户");
        }
        String appId = authorizeData.getAppId();//appId
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("createApp", appId));
        databaseManageCriteria.add(Restrictions.eq("databaseName", appId + "_" + saveDatabaseManageRequestDTO.getDatabaseName()));
        List<DatabaseManage> databaseManageList = super.findAllEntity(databaseManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseManageList)) {
            throw new BusinessException("该app已经存在该数据库，请重新输入一个数据库名称进行创建");
        }
        String jdbcClientConfigPropertiesUrl = jdbcClientConfigProperties.getUrl();
        String[] split = jdbcClientConfigPropertiesUrl.split("//");
        String[] split1 = split[1].split("/");
        String ipPort = split1[0];//获得IP和端口
        DatabaseManage databaseManage = new DatabaseManage();
//        ESBeanUtils.copyProperties(saveDatabaseManageRequestDTO, databaseManage);
        databaseManage.setDatabaseName(appId + "_" + saveDatabaseManageRequestDTO.getDatabaseName());
        databaseManage.setUrl("jdbc:mysql://" + ipPort + "/" + appId + "_" + saveDatabaseManageRequestDTO.getDatabaseName() + "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
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
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseManageRequestDTO, databaseManage);
        return super.save(databaseManage);
    }

    @Override
    public List<DatabaseManageResponseDTO> list(ListDatabaseManageRequestDTO listDatabaseManageRequestDTO) {
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(databaseManageCriteria, listDatabaseManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseManageResponseDTO> page(PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO) {
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(databaseManageCriteria, pageDatabaseManageRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseManageResponseDTO generate(DatabaseManage databaseManage) {
        DatabaseManageResponseDTO databaseManageResponseDTO = new DatabaseManageResponseDTO();
        ESBeanUtils.copyProperties(databaseManage, databaseManageResponseDTO);
        return databaseManageResponseDTO;
    }
}