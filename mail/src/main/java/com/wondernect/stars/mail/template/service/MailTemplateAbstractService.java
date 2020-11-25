package com.wondernect.stars.mail.template.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.template.dto.ListMailTemplateRequestDTO;
import com.wondernect.stars.mail.template.dto.MailTemplateResponseDTO;
import com.wondernect.stars.mail.template.dto.PageMailTemplateRequestDTO;
import com.wondernect.stars.mail.template.dto.SaveMailTemplateRequestDTO;
import com.wondernect.stars.mail.template.model.MailTemplate;
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
//TODO:判断对象是否存在

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
//TODO:添加列表筛选条件

        return super.findAll(mailTemplateCriteria, listMailTemplateRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailTemplateResponseDTO> page(PageMailTemplateRequestDTO pageMailTemplateRequestDTO) {
        Criteria<MailTemplate> mailTemplateCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(mailTemplateCriteria, pageMailTemplateRequestDTO.getPageRequestData());
    }

    @Override
    public MailTemplateResponseDTO generate(MailTemplate mailTemplate) {
        MailTemplateResponseDTO mailTemplateResponseDTO = new MailTemplateResponseDTO();
        ESBeanUtils.copyProperties(mailTemplate, mailTemplateResponseDTO);
        return mailTemplateResponseDTO;
    }
}