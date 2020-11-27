package com.wondernect.stars.mail.service.server;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.server.ListMailServerRequestDTO;
import com.wondernect.stars.mail.dto.server.MailServerResponseDTO;
import com.wondernect.stars.mail.dto.server.PageMailServerRequestDTO;
import com.wondernect.stars.mail.dto.server.SaveMailServerRequestDTO;
import com.wondernect.stars.mail.model.MailServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 邮箱服务器服务抽象实现类
 *
 * @author 王威 2020-11-23 15:55:47
 **/
@Service
public abstract class MailServerAbstractService extends BaseStringService<MailServerResponseDTO, MailServer> implements MailServerInterface {

    @Transactional
    @Override
    public MailServerResponseDTO create(SaveMailServerRequestDTO saveMailServerRequestDTO) {
        MailServer mailServer = new MailServer();
        ESBeanUtils.copyProperties(saveMailServerRequestDTO, mailServer);
        return super.save(mailServer);
    }

    @Transactional
    @Override
    public MailServerResponseDTO update(String id, SaveMailServerRequestDTO saveMailServerRequestDTO) {
        MailServer mailServer = super.findEntityById(id);
        if (ESObjectUtils.isNull(mailServer)) {
            throw new BusinessException("邮箱服务器不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveMailServerRequestDTO, mailServer);
        return super.save(mailServer);
    }

    @Override
    public List<MailServerResponseDTO> list(ListMailServerRequestDTO listMailServerRequestDTO) {
        Criteria<MailServer> mailServerCriteria = new Criteria<>();
        mailServerCriteria.add(Restrictions.eq("type", listMailServerRequestDTO.getType()));
        return super.findAll(mailServerCriteria, listMailServerRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<MailServerResponseDTO> page(PageMailServerRequestDTO pageMailServerRequestDTO) {
        Criteria<MailServer> mailServerCriteria = new Criteria<>();
        mailServerCriteria.add(Restrictions.eq("type", pageMailServerRequestDTO.getType()));
        return super.findAll(mailServerCriteria, pageMailServerRequestDTO.getPageRequestData());
    }

    @Override
    public MailServerResponseDTO generate(MailServer mailServer) {
        MailServerResponseDTO mailServerResponseDTO = new MailServerResponseDTO();
        ESBeanUtils.copyProperties(mailServer, mailServerResponseDTO);
        return mailServerResponseDTO;
    }
}