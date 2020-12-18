package com.wondernect.stars.database.service.databaseRootManage;

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
import com.wondernect.stars.database.model.DatabaseManage;
import com.wondernect.stars.database.model.DatabaseRootManage;
import com.wondernect.stars.database.service.databaseManage.DatabaseManageService;
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
    private DatabaseManageService databaseManageService;

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO create(SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        DatabaseRootManage databaseRootManage = new DatabaseRootManage();
        ESBeanUtils.copyProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        /*databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");//mysql驱动及连接
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
        */
        return super.save(databaseRootManage);
    }

    @Transactional
    @Override
    public DatabaseRootManageResponseDTO update(String id, SaveDatabaseRootManageRequestDTO saveDatabaseRootManageRequestDTO) {
        DatabaseRootManage databaseRootManage = super.findEntityById(id);
        /*if (ESObjectUtils.isNull(databaseRootManage)) {
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
        }*/
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveDatabaseRootManageRequestDTO, databaseRootManage);
        /*databaseRootManage.setDriver("com.mysql.cj.jdbc.Driver");//mysql驱动及连接
        databaseRootManage.setUrl("jdbc:mysql://" + saveDatabaseRootManageRequestDTO.getIp() + ":" + saveDatabaseRootManageRequestDTO.getPort() + "/mysql?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC");
       */
        return super.save(databaseRootManage);
    }

    @Transactional
    public void delete(String id) {
        DatabaseRootManage databaseRootManage = super.findEntityById(id);
        if (ESObjectUtils.isNull(databaseRootManage)) {
            throw new BusinessException("要删除的数据库不存在");
        }
        Criteria<DatabaseManage> databaseManageCriteria = new Criteria<>();
        databaseManageCriteria.add(Restrictions.eq("databaseRootManageId", id));
        List<DatabaseManage> databaseManageList = databaseManageService.findAllEntity(databaseManageCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(databaseManageList)) {
            throw new BusinessException("要删除的数据库还存在其他数据库名称管理，不允许删除");
        }
        super.deleteById(id);
    }

    @Override
    public List<DatabaseRootManageResponseDTO> list(ListDatabaseRootManageRequestDTO listDatabaseRootManageRequestDTO) {
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("ip", listDatabaseRootManageRequestDTO.getIp()));
        return super.findAll(databaseRootManageCriteria, listDatabaseRootManageRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<DatabaseRootManageResponseDTO> page(PageDatabaseRootManageRequestDTO pageDatabaseRootManageRequestDTO) {
        Criteria<DatabaseRootManage> databaseRootManageCriteria = new Criteria<>();
        databaseRootManageCriteria.add(Restrictions.eq("ip", pageDatabaseRootManageRequestDTO.getIp()));
        return super.findAll(databaseRootManageCriteria, pageDatabaseRootManageRequestDTO.getPageRequestData());
    }

    @Override
    public DatabaseRootManageResponseDTO generate(DatabaseRootManage databaseRootManage) {
        DatabaseRootManageResponseDTO databaseRootManageResponseDTO = new DatabaseRootManageResponseDTO();
        ESBeanUtils.copyProperties(databaseRootManage, databaseRootManageResponseDTO);
        return databaseRootManageResponseDTO;
    }
}