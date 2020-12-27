package com.wondernect.stars.sms.param.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.param.dto.ListSMSTemplateParamRequestDTO;
import com.wondernect.stars.sms.param.dto.PageSMSTemplateParamRequestDTO;
import com.wondernect.stars.sms.param.dto.SMSTemplateParamResponseDTO;
import com.wondernect.stars.sms.param.dto.SaveSMSTemplateParamRequestDTO;
import com.wondernect.stars.sms.param.model.SMSTemplateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 短信模板变量服务抽象实现类
 *
 * @author chenxun 2020-12-27 11:54:44
 **/
@Service
public abstract class SMSTemplateParamAbstractService extends BaseStringService<SMSTemplateParamResponseDTO, SMSTemplateParam> implements SMSTemplateParamInterface {

    @Transactional
    @Override
    public SMSTemplateParamResponseDTO create(SaveSMSTemplateParamRequestDTO saveSMSTemplateParamRequestDTO) {
        Criteria<SMSTemplateParam> smsTemplateParamCriteria = new Criteria<>();
        smsTemplateParamCriteria.add(Restrictions.eq("templateId", saveSMSTemplateParamRequestDTO.getTemplateId()));
        smsTemplateParamCriteria.add(Restrictions.eq("param", saveSMSTemplateParamRequestDTO.getParam()));
        if (ESObjectUtils.isNotNull(super.findOneEntity(smsTemplateParamCriteria, new ArrayList<>()))) {
            throw new BusinessException("模板对应变量已存在,请核对后重试");
        }
        SMSTemplateParam sMSTemplateParam = new SMSTemplateParam();
        ESBeanUtils.copyProperties(saveSMSTemplateParamRequestDTO, sMSTemplateParam);
        return super.save(sMSTemplateParam);
    }

    @Transactional
    @Override
    public SMSTemplateParamResponseDTO update(String id, SaveSMSTemplateParamRequestDTO saveSMSTemplateParamRequestDTO) {
        SMSTemplateParam sMSTemplateParam = super.findEntityById(id);
        if (ESObjectUtils.isNull(sMSTemplateParam)) {
            throw new BusinessException("短信模板变量不存在");
        }
        if (!ESStringUtils.equals(sMSTemplateParam.getParam(), saveSMSTemplateParamRequestDTO.getParam())) {
            Criteria<SMSTemplateParam> smsTemplateParamCriteria = new Criteria<>();
            smsTemplateParamCriteria.add(Restrictions.eq("templateId", saveSMSTemplateParamRequestDTO.getTemplateId()));
            smsTemplateParamCriteria.add(Restrictions.eq("param", saveSMSTemplateParamRequestDTO.getParam()));
            if (ESObjectUtils.isNotNull(super.findOneEntity(smsTemplateParamCriteria, new ArrayList<>()))) {
                throw new BusinessException("模板对应变量已存在,请核对后重试");
            }
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveSMSTemplateParamRequestDTO, sMSTemplateParam);
        return super.save(sMSTemplateParam);
    }

    @Override
    public List<SMSTemplateParamResponseDTO> list(ListSMSTemplateParamRequestDTO listSMSTemplateParamRequestDTO) {
        Criteria<SMSTemplateParam> sMSTemplateParamCriteria = new Criteria<>();
        sMSTemplateParamCriteria.add(Restrictions.eq("templateId", listSMSTemplateParamRequestDTO.getTemplateId()));
        sMSTemplateParamCriteria.add(Restrictions.eq("param", listSMSTemplateParamRequestDTO.getParam()));
        return super.findAll(sMSTemplateParamCriteria, listSMSTemplateParamRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<SMSTemplateParamResponseDTO> page(PageSMSTemplateParamRequestDTO pageSMSTemplateParamRequestDTO) {
        Criteria<SMSTemplateParam> sMSTemplateParamCriteria = new Criteria<>();
        sMSTemplateParamCriteria.add(Restrictions.eq("templateId", pageSMSTemplateParamRequestDTO.getTemplateId()));
        sMSTemplateParamCriteria.add(Restrictions.eq("param", pageSMSTemplateParamRequestDTO.getParam()));
        return super.findAll(sMSTemplateParamCriteria, pageSMSTemplateParamRequestDTO.getPageRequestData());
    }

    @Override
    public SMSTemplateParamResponseDTO generate(SMSTemplateParam sMSTemplateParam) {
        SMSTemplateParamResponseDTO sMSTemplateParamResponseDTO = new SMSTemplateParamResponseDTO();
        ESBeanUtils.copyProperties(sMSTemplateParam, sMSTemplateParamResponseDTO);
        return sMSTemplateParamResponseDTO;
    }
}