package com.wondernect.stars.mail.service.template;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.template.ListMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.MailTemplateResponseDTO;
import com.wondernect.stars.mail.dto.template.PageMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.SaveMailTemplateRequestDTO;
import com.wondernect.stars.mail.model.MailTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件模板服务抽象实现类
 *
 * @author 王威 2020-11-23 15:56:11
 **/
@Service
public abstract class MailTemplateAbstractService extends BaseStringService<MailTemplateResponseDTO, MailTemplate> implements MailTemplateInterface {

    @Transactional
    @Override
    public MailTemplateResponseDTO create(SaveMailTemplateRequestDTO saveMailTemplateRequestDTO) {
        MailTemplate mailTemplate = new MailTemplate();
        ESBeanUtils.copyProperties(saveMailTemplateRequestDTO, mailTemplate);
        return super.save(mailTemplate);
    }

    @Transactional
    @Override
    public MailTemplateResponseDTO update(String id, SaveMailTemplateRequestDTO saveMailTemplateRequestDTO) {
        MailTemplate mailTemplate = super.findEntityById(id);
        if (ESObjectUtils.isNull(mailTemplate)) {
            throw new BusinessException("邮件模板不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveMailTemplateRequestDTO, mailTemplate);
        return super.save(mailTemplate);
    }

    @Override
    public List<MailTemplateResponseDTO> list(ListMailTemplateRequestDTO listMailTemplateRequestDTO) {
        Criteria<MailTemplate> mailTemplateCriteria = new Criteria<>();
        mailTemplateCriteria.add(Restrictions.eq("type", listMailTemplateRequestDTO.getType()));
        return super.findAll(mailTemplateCriteria, listMailTemplateRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailTemplateResponseDTO> page(PageMailTemplateRequestDTO pageMailTemplateRequestDTO) {
        Criteria<MailTemplate> mailTemplateCriteria = new Criteria<>();
        mailTemplateCriteria.add(Restrictions.eq("type", pageMailTemplateRequestDTO.getType()));
        return super.findAll(mailTemplateCriteria, pageMailTemplateRequestDTO.getPageRequestData());
    }

    @Override
    public MailTemplateResponseDTO generate(MailTemplate mailTemplate) {
        MailTemplateResponseDTO mailTemplateResponseDTO = new MailTemplateResponseDTO();
        ESBeanUtils.copyProperties(mailTemplate, mailTemplateResponseDTO);
        return mailTemplateResponseDTO;
    }
}