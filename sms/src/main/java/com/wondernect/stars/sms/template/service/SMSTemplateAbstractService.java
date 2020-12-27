package com.wondernect.stars.sms.template.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.template.dto.ListSMSTemplateRequestDTO;
import com.wondernect.stars.sms.template.dto.PageSMSTemplateRequestDTO;
import com.wondernect.stars.sms.template.dto.SMSTemplateResponseDTO;
import com.wondernect.stars.sms.template.dto.SaveSMSTemplateRequestDTO;
import com.wondernect.stars.sms.template.model.SMSTemplate;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短信模板服务抽象实现类
 *
 * @author chenxun 2020-12-27 11:54:10
 **/
@Service
public abstract class SMSTemplateAbstractService extends BaseStringService<SMSTemplateResponseDTO, SMSTemplate> implements SMSTemplateInterface {

    @Transactional
    @Override
    public SMSTemplateResponseDTO create(SaveSMSTemplateRequestDTO saveSMSTemplateRequestDTO) {
        SMSTemplate sMSTemplate = new SMSTemplate();
        ESBeanUtils.copyProperties(saveSMSTemplateRequestDTO, sMSTemplate);
        return super.save(sMSTemplate);
    }

    @Transactional
    @Override
    public SMSTemplateResponseDTO update(String id, SaveSMSTemplateRequestDTO saveSMSTemplateRequestDTO) {
        SMSTemplate sMSTemplate = super.findEntityById(id);
        if (ESObjectUtils.isNull(sMSTemplate)) {
            throw new BusinessException("短信模板不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveSMSTemplateRequestDTO, sMSTemplate);
        return super.save(sMSTemplate);
    }

    @Override
    public List<SMSTemplateResponseDTO> list(ListSMSTemplateRequestDTO listSMSTemplateRequestDTO) {
        Criteria<SMSTemplate> sMSTemplateCriteria = new Criteria<>();
        sMSTemplateCriteria.add(Restrictions.like("name", listSMSTemplateRequestDTO.getName(), MatchMode.ANYWHERE));
        return super.findAll(sMSTemplateCriteria, listSMSTemplateRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<SMSTemplateResponseDTO> page(PageSMSTemplateRequestDTO pageSMSTemplateRequestDTO) {
        Criteria<SMSTemplate> sMSTemplateCriteria = new Criteria<>();
        sMSTemplateCriteria.add(Restrictions.like("name", pageSMSTemplateRequestDTO.getName(), MatchMode.ANYWHERE));
        return super.findAll(sMSTemplateCriteria, pageSMSTemplateRequestDTO.getPageRequestData());
    }

    @Override
    public SMSTemplateResponseDTO generate(SMSTemplate sMSTemplate) {
        SMSTemplateResponseDTO sMSTemplateResponseDTO = new SMSTemplateResponseDTO();
        ESBeanUtils.copyProperties(sMSTemplate, sMSTemplateResponseDTO);
        return sMSTemplateResponseDTO;
    }
}