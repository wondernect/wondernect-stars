package com.wondernect.stars.rbac.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.stars.rbac.model.Operation;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: OperationManager
 * Author: chenxun
 * Date: 2020-06-23 10:35
 * Description:
 */
@Service
public class OperationManager extends BaseStringManager<Operation> {

    public Operation findByOperationIdAndMenuId(String operationId, String menuId) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuId", menuId));
        operationCriteria.add(Restrictions.eq("id", operationId));
        List<Operation> operationList = super.findAll(operationCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(operationList)) {
            return null;
        }
        return operationList.get(0);
    }

    public Operation findByOperationCodeAndMenuId(String operationCode, String menuId) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuId", menuId));
        operationCriteria.add(Restrictions.eq("code", operationCode));
        List<Operation> operationList = super.findAll(operationCriteria, Arrays.asList(new SortData("createTime", "DESC")));
        if (CollectionUtils.isEmpty(operationList)) {
            return null;
        }
        return operationList.get(0);
    }

    @Transactional
    public void deleteAllByMenuId(String menuId) {
        Criteria<Operation> operationCriteria = new Criteria<>();
        operationCriteria.add(Restrictions.eq("menuId", menuId));
        List<Operation> operationList = super.findAll(operationCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(operationList)) {
            for (Operation operation : operationList) {
                super.deleteById(operation.getId());
            }
        }
    }
}
