package com.wondernect.stars.sms.sms.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.sms.dto.ListSMSRequestDTO;
import com.wondernect.stars.sms.sms.dto.PageSMSRequestDTO;
import com.wondernect.stars.sms.sms.dto.SMSResponseDTO;
import com.wondernect.stars.sms.sms.dto.SaveSMSRequestDTO;
import com.wondernect.stars.sms.sms.model.SMS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短信服务抽象实现类
 *
 * @author chenxun 2020-12-27 11:53:25
 **/
@Service
public abstract class SMSAbstractService extends BaseStringService<SMSResponseDTO, SMS> implements SMSInterface {

    @Transactional
    @Override
    public SMSResponseDTO create(SaveSMSRequestDTO saveSMSRequestDTO) {
        SMS sMS = new SMS();
        ESBeanUtils.copyProperties(saveSMSRequestDTO, sMS);
        return super.save(sMS);
    }

    @Transactional
    @Override
    public SMSResponseDTO update(String id, SaveSMSRequestDTO saveSMSRequestDTO) {
        SMS sMS = super.findEntityById(id);
        if (ESObjectUtils.isNull(sMS)) {
            throw new BusinessException("短信不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveSMSRequestDTO, sMS);
        return super.save(sMS);
    }

    @Override
    public List<SMSResponseDTO> list(ListSMSRequestDTO listSMSRequestDTO) {
        Criteria<SMS> sMSCriteria = new Criteria<>();
        sMSCriteria.add(Restrictions.eq("phoneNumber", listSMSRequestDTO.getPhoneNumber()));
        sMSCriteria.add(Restrictions.eq("sendResult", listSMSRequestDTO.getSendResult()));
        sMSCriteria.add(Restrictions.eq("smsTemplateId", listSMSRequestDTO.getSmsTemplateId()));
        return super.findAll(sMSCriteria, listSMSRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<SMSResponseDTO> page(PageSMSRequestDTO pageSMSRequestDTO) {
        Criteria<SMS> sMSCriteria = new Criteria<>();
        sMSCriteria.add(Restrictions.eq("phoneNumber", pageSMSRequestDTO.getPhoneNumber()));
        sMSCriteria.add(Restrictions.eq("sendResult", pageSMSRequestDTO.getSendResult()));
        sMSCriteria.add(Restrictions.eq("smsTemplateId", pageSMSRequestDTO.getSmsTemplateId()));
        return super.findAll(sMSCriteria, pageSMSRequestDTO.getPageRequestData());
    }

    @Override
    public SMSResponseDTO generate(SMS sMS) {
        SMSResponseDTO sMSResponseDTO = new SMSResponseDTO();
        ESBeanUtils.copyProperties(sMS, sMSResponseDTO);
        return sMSResponseDTO;
    }
}