package com.wondernect.stars.logger.dao;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.stars.logger.model.RequestLog;
import org.springframework.stereotype.Repository;

/**
 * 日志数据库操作类
 *
 * @author chenxun 2020-12-17 14:58:50
 **/
@Repository
public class RequestLogDao extends BaseStringDao<RequestLog> {
}