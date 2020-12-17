package com.wondernect.stars.logger.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.logger.dto.ListRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.PageRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.RequestLogResponseDTO;
import com.wondernect.stars.logger.dto.SaveRequestLogRequestDTO;
import com.wondernect.stars.logger.model.RequestLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志服务抽象实现类
 *
 * @author chenxun 2020-12-17 14:58:51
 **/
@Service
public abstract class RequestLogAbstractService extends BaseStringService<RequestLogResponseDTO, RequestLog> implements RequestLogInterface {

    @Transactional
    @Override
    public RequestLogResponseDTO create(SaveRequestLogRequestDTO saveRequestLogRequestDTO) {
        RequestLog requestLog = new RequestLog();
        ESBeanUtils.copyProperties(saveRequestLogRequestDTO, requestLog);
        return super.save(requestLog);
    }

    @Transactional
    @Override
    public RequestLogResponseDTO update(String id, SaveRequestLogRequestDTO saveRequestLogRequestDTO) {
        RequestLog requestLog = super.findEntityById(id);
        if (ESObjectUtils.isNull(requestLog)) {
            throw new BusinessException("日志不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveRequestLogRequestDTO, requestLog);
        return super.save(requestLog);
    }

    @Override
    public List<RequestLogResponseDTO> list(ListRequestLogRequestDTO listRequestLogRequestDTO) {
        Criteria<RequestLog> requestLogCriteria = new Criteria<>();
        requestLogCriteria.add(Restrictions.eq("level", listRequestLogRequestDTO.getLevel()));
        requestLogCriteria.add(Restrictions.eq("service", listRequestLogRequestDTO.getService()));
        requestLogCriteria.add(Restrictions.eq("module", listRequestLogRequestDTO.getModule()));
        requestLogCriteria.add(Restrictions.eq("userId", listRequestLogRequestDTO.getUserId()));
        requestLogCriteria.add(Restrictions.eq("appId", listRequestLogRequestDTO.getAppId()));
        requestLogCriteria.add(Restrictions.eq("operation", listRequestLogRequestDTO.getOperation()));
        requestLogCriteria.add(Restrictions.eq("requestId", listRequestLogRequestDTO.getRequestId()));
        requestLogCriteria.add(Restrictions.eq("url", listRequestLogRequestDTO.getUrl()));
        return super.findAll(requestLogCriteria, listRequestLogRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<RequestLogResponseDTO> page(PageRequestLogRequestDTO pageRequestLogRequestDTO) {
        Criteria<RequestLog> requestLogCriteria = new Criteria<>();
        requestLogCriteria.add(Restrictions.eq("level", pageRequestLogRequestDTO.getLevel()));
        requestLogCriteria.add(Restrictions.eq("service", pageRequestLogRequestDTO.getService()));
        requestLogCriteria.add(Restrictions.eq("module", pageRequestLogRequestDTO.getModule()));
        requestLogCriteria.add(Restrictions.eq("userId", pageRequestLogRequestDTO.getUserId()));
        requestLogCriteria.add(Restrictions.eq("appId", pageRequestLogRequestDTO.getAppId()));
        requestLogCriteria.add(Restrictions.eq("operation", pageRequestLogRequestDTO.getOperation()));
        requestLogCriteria.add(Restrictions.eq("requestId", pageRequestLogRequestDTO.getRequestId()));
        requestLogCriteria.add(Restrictions.eq("url", pageRequestLogRequestDTO.getUrl()));
        return super.findAll(requestLogCriteria, pageRequestLogRequestDTO.getPageRequestData());
    }

    @Override
    public RequestLogResponseDTO generate(RequestLog requestLog) {
        RequestLogResponseDTO requestLogResponseDTO = new RequestLogResponseDTO();
        ESBeanUtils.copyProperties(requestLog, requestLogResponseDTO);
        return requestLogResponseDTO;
    }
}