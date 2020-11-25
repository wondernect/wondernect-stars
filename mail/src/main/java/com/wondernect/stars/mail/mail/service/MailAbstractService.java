package com.wondernect.stars.mail.mail.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.mail.dto.ListMailRequestDTO;
import com.wondernect.stars.mail.mail.dto.MailResponseDTO;
import com.wondernect.stars.mail.mail.dto.PageMailRequestDTO;
import com.wondernect.stars.mail.mail.dto.SaveMailRequestDTO;
import com.wondernect.stars.mail.mail.model.Mail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮件服务抽象实现类
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@Service
public abstract class MailAbstractService extends BaseStringService<MailResponseDTO, Mail> implements MailInterface {

    @Transactional
    @Override
    public MailResponseDTO create(SaveMailRequestDTO saveMailRequestDTO) {
//TODO:判断对象是否存在

        Mail mail = new Mail();
        ESBeanUtils.copyProperties(saveMailRequestDTO, mail);
        return super.save(mail);
    }

    @Transactional
    @Override
    public MailResponseDTO update(String id, SaveMailRequestDTO saveMailRequestDTO) {
        Mail mail = super.findEntityById(id);
        if (ESObjectUtils.isNull(mail)) {
            throw new BusinessException("邮件不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveMailRequestDTO, mail);
        return super.save(mail);
    }

    @Override
    public List<MailResponseDTO> list(ListMailRequestDTO listMailRequestDTO) {
        Criteria<Mail> mailCriteria = new Criteria<>();
//TODO:添加列表筛选条件

        return super.findAll(mailCriteria, listMailRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailResponseDTO> page(PageMailRequestDTO pageMailRequestDTO) {
        Criteria<Mail> mailCriteria = new Criteria<>();
//TODO:添加分页筛选条件

        return super.findAll(mailCriteria, pageMailRequestDTO.getPageRequestData());
    }

    @Override
    public MailResponseDTO generate(Mail mail) {
        MailResponseDTO mailResponseDTO = new MailResponseDTO();
        ESBeanUtils.copyProperties(mail, mailResponseDTO);
        return mailResponseDTO;
    }
}