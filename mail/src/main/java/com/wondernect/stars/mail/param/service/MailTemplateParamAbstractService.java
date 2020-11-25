package com.wondernect.stars.mail.param.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.param.dto.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.param.dto.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.param.dto.PageMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.param.dto.SaveMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.param.model.MailTemplateParam;
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
//TODO:判断对象是否存在

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
//TODO:添加列表筛选条件

        return super.findAll(mailTemplateParamCriteria, listMailTemplateParamRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailTemplateParamResponseDTO> page(PageMailTemplateParamRequestDTO pageMailTemplateParamRequestDTO) {
        Criteria<MailTemplateParam> mailTemplateParamCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(mailTemplateParamCriteria, pageMailTemplateParamRequestDTO.getPageRequestData());
    }

    @Override
    public MailTemplateParamResponseDTO generate(MailTemplateParam mailTemplateParam) {
        MailTemplateParamResponseDTO mailTemplateParamResponseDTO = new MailTemplateParamResponseDTO();
        ESBeanUtils.copyProperties(mailTemplateParam, mailTemplateParamResponseDTO);
        return mailTemplateParamResponseDTO;
    }
}