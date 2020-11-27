package com.wondernect.stars.mail.service.param;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.param.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.dto.param.PageMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.SaveMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.model.MailTemplateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件模板参数服务抽象实现类
 *
 * @author 王威 2020-11-23 15:55:11
 **/
@Service
public abstract class MailTemplateParamAbstractService extends BaseStringService<MailTemplateParamResponseDTO, MailTemplateParam> implements MailTemplateParamInterface {

    @Transactional
    @Override
    public MailTemplateParamResponseDTO create(SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO) {
        MailTemplateParam mailTemplateParam = new MailTemplateParam();
        ESBeanUtils.copyProperties(saveMailTemplateParamRequestDTO, mailTemplateParam);
        return super.save(mailTemplateParam);
    }

    @Transactional
    @Override
    public MailTemplateParamResponseDTO update(String id, SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO) {
        MailTemplateParam mailTemplateParam = super.findEntityById(id);
        if (ESObjectUtils.isNull(mailTemplateParam)) {
            throw new BusinessException("邮件模板参数不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveMailTemplateParamRequestDTO, mailTemplateParam);
        return super.save(mailTemplateParam);
    }

    @Override
    public List<MailTemplateParamResponseDTO> list(ListMailTemplateParamRequestDTO listMailTemplateParamRequestDTO) {
        Criteria<MailTemplateParam> mailTemplateParamCriteria = new Criteria<>();
        mailTemplateParamCriteria.add(Restrictions.eq("templateId", listMailTemplateParamRequestDTO.getTemplateId()));
        return super.findAll(mailTemplateParamCriteria, listMailTemplateParamRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailTemplateParamResponseDTO> page(PageMailTemplateParamRequestDTO pageMailTemplateParamRequestDTO) {
        Criteria<MailTemplateParam> mailTemplateParamCriteria = new Criteria<>();
        mailTemplateParamCriteria.add(Restrictions.eq("templateId", pageMailTemplateParamRequestDTO.getTemplateId()));
        return super.findAll(mailTemplateParamCriteria, pageMailTemplateParamRequestDTO.getPageRequestData());
    }

    @Override
    public MailTemplateParamResponseDTO generate(MailTemplateParam mailTemplateParam) {
        MailTemplateParamResponseDTO mailTemplateParamResponseDTO = new MailTemplateParamResponseDTO();
        ESBeanUtils.copyProperties(mailTemplateParam, mailTemplateParamResponseDTO);
        return mailTemplateParamResponseDTO;
    }
}