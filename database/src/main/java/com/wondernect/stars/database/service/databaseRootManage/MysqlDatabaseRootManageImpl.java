package com.wondernect.stars.database.service.databaseRootManage;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.database.dto.DatabaseRootManageResponseDTO;
import com.wondernect.stars.database.dto.SaveDatabaseRootManageRequestDTO;
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import com.wondernect.stars.database.service.databaseManage.DatabaseManageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MysqlDatabaseRootManageImpl extends DatabaseRootManageService implements DatabaseRootManageInterface {

    @Autowired
    private DatabaseRootManageService databaseRootManageService;

    @Autowired
    private DatabaseManageService databaseManageService;

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        DatabaseRootManage databaseRootManage = new DatabaseRootManage();
        ESBeanUtils.copyProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");//mysql驱动及连接
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        return databaseRootManageService.save(databaseRootManage);
    }

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        DatabaseRootManage databaseRootManage = databaseRootManageService.findEntityById(id);
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("数据库不存在");
        }
        //如果ip或者port发生改变，就需要修改数据库名称那里的连接url
        if (!databaseRootManage.getIp().equals(saveDatabaseRootManageRequestDTO.getIp()) || !databaseRootManage.getPort().equals(saveDatabaseRootManageRequestDTO.getPort())) {
            Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
            databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", id));
            List<DatabaseManage> databaseManageList = databaseManageService.findAllEntity(databaseManageCriteria, new ArrayList<>());
            if (CollectionUtils.isNotEmpty(databaseManageList)) {
                for (DatabaseManage databaseManage : databaseManageList) {
                    databaseManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/" + databaseManage.getDatabaseName() + "?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
                    databaseManageService.save(databaseManage);
                }
            }
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");//mysql驱动及连接
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        return databaseRootManageService.save(databaseRootManage);
    }

}
