package com.wondernect.stars.database.service.databaseManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.jdbc.client.response.JDBCResult;
import com.wondernect.elements.jdbc.client.util.JDBCClient;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import com.wondernect.stars.database.service.databaseRootManage.DatabaseRootManageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MysqlDatabaseManageImpl extends DatabaseManageService implements DatabaseManageInterface {

    @Autowired
    private JDBCClient jdbcClient;

    @Autowired
    private DatabaseManageService databaseManageService;

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    @Transactional
    @Override
    public DatabaseManageResponseDTO create(SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(saveDatabaseManageRequestDTO.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("您选择的数据库不存在");
        }
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", saveDatabaseManageRequestDTO.getDatabaseRootManageId()));
        databaseManageCriteria.add(Restrictions.eq("databaseName", saveDatabaseManageRequestDTO.getDatabaseName()));
        List<DatabaseManage> databaseManageList = databaseManageService.findAllEntity(databaseManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseManageList)) {
            throw new BusinessException("该数据库已存在名为" + saveDatabaseManageRequestDTO.getDatabaseName() + "的数据库，请重新输入一个数据库名称进行创建");
        }
        String ip = databaseRootManage.getIp();
        String port = String.valueOf(databaseRootManage.getPort());
        DatabaseManage databaseManage = new DatabaseManage();
        databaseManage.setDatabaseRootManageId(databaseRootManage.getId());
        databaseManage.setDatabaseName(saveDatabaseManageRequestDTO.getDatabaseName());
        databaseManage.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + saveDatabaseManageRequestDTO.getDatabaseName() + "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");//mysql的连接
        databaseManage.setInitState(false);
        databaseManage.setInitMessage("数据库尚未初始化");
        databaseManage.setType(databaseRootManage.getType().toString());
        return databaseManageService.save(databaseManage);
    }

    @Transactional
    @Override
    public DatabaseManageResponseDTO update(String id, SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO) {
        DatabaseManage databaseManage = databaseManageService.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("数据库名称不存在");
        }
        if (databaseManage.getInitState()) {
            throw new BusinessException("数据库已初始化，不允许修改");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(saveDatabaseManageRequestDTO.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("您选择的数据库不存在");
        }
        String ip = databaseRootManage.getIp();
        String port = String.valueOf(databaseRootManage.getPort());
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseManageRequestDTO, databaseManage);
        databaseManage.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + saveDatabaseManageRequestDTO.getDatabaseName() + "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");//mysql的连接
        return databaseManageService.save(databaseManage);
    }

    @Transactional
    public DatabaseManageResponseDTO initDatabase(String id) {
        DatabaseManage databaseManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseManage)) {
            throw new BusinessException("要初始化的数据库不存在");
        }
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(databaseManage.getDatabaseRootManageId());
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要初始化的数据库不存在数据库管理服务");
        }
        JDBCResult jdbcResult = jdbcClient.initDatabase(databaseRootManage.getDriver(), databaseRootManage.getUrl(), databaseRootManage.getUsername(), databaseRootManage.getPassword(), databaseManage.getDatabaseName());
        databaseManage.setInitState(jdbcResult.getResult());
        databaseManage.setInitMessage(jdbcResult.getMessage());
        return super.save(databaseManage);
    }

}
