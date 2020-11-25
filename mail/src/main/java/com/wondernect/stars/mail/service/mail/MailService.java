package com.wondernect.stars.mail.service.mail;

import com.wondernect.elements.mail.client.impl.DefaultMailClient;
import com.wondernect.elements.mail.client.util.MailSendResult;
import com.wondernect.elements.thymeleaf.StringTemplateThymeleaf;
import com.wondernect.elements.thymeleaf.UrlTemplateThymeleaf;
import com.wondernect.stars.mail.dto.mail.SendMailRequestDTO;
import com.wondernect.stars.mail.dto.param.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.dto.server.MailServerResponseDTO;
import com.wondernect.stars.mail.dto.template.MailTemplateResponseDTO;
import com.wondernect.stars.mail.em.MailTemplateType;
import com.wondernect.stars.mail.service.param.MailTemplateParamService;
import com.wondernect.stars.mail.service.server.MailServerService;
import com.wondernect.stars.mail.service.template.MailTemplateService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 邮件服务
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@Service
public class MailService extends MailAbstractService {

    @Autowired
    private StringTemplateThymeleaf stringTemplateThymeleaf;

    @Autowired
    private UrlTemplateThymeleaf urlTemplateThymeleaf;

    @Autowired
    private DefaultMailClient defaultMailClient;

    @Autowired
    private MailServerService mailServerService;

    @Autowired
    private MailTemplateService mailTemplateService;

    @Autowired
    private MailTemplateParamService mailTemplateParamService;

    public MailSendResult send(SendMailRequestDTO sendMailRequestDTO) {
        MailServerResponseDTO mailServerResponseDTO = mailServerService.findById(sendMailRequestDTO.getMailServerId());
        String host = mailServerResponseDTO.getHost();
        int port = mailServerResponseDTO.getPort();
        String username = mailServerResponseDTO.getUsername();
        String password = mailServerResponseDTO.getPassword();
        MailTemplateResponseDTO mailTemplateResponseDTO = mailTemplateService.findById(sendMailRequestDTO.getMailTemplateId());
        ListMailTemplateParamRequestDTO listMailTemplateParamRequestDTO = new ListMailTemplateParamRequestDTO();
        listMailTemplateParamRequestDTO.setSearchText(sendMailRequestDTO.getMailTemplateId());
        List<MailTemplateParamResponseDTO> list =
                mailTemplateParamService.list(listMailTemplateParamRequestDTO);
        Map<String, Object> map = new HashMap<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (MailTemplateParamResponseDTO mailTemplateParamResponseDTO : list) {
                Object value = sendMailRequestDTO.getAttachment().get(mailTemplateParamResponseDTO.getParam());
                map.put(mailTemplateParamResponseDTO.getName(), value);
            }
        } else {
            map = null;
        }
        if (mailTemplateResponseDTO.getType() == MailTemplateType.PLAIN_TEXT) {
            MailSendResult mailSendResult = defaultMailClient.sendPlainTextMail(host, port, username, password, mailTemplateResponseDTO.getPersonal(),
                    sendMailRequestDTO.getToAddress(), mailTemplateResponseDTO.getSubject(), mailTemplateResponseDTO.getContent(),
                    sendMailRequestDTO.getAttachment());
            return mailSendResult;
        } else if ((mailTemplateResponseDTO.getType() == MailTemplateType.HTML_STRING)) {
            String mailText = stringTemplateThymeleaf.generateContent(mailTemplateResponseDTO.getContent(), map);
            MailSendResult mailSendResult = defaultMailClient.sendHtmlMail(host, port, username, password, mailTemplateResponseDTO.getPersonal(),
                    sendMailRequestDTO.getToAddress(), mailTemplateResponseDTO.getSubject(), mailText);
            return mailSendResult;
        } else {
            String mailText = urlTemplateThymeleaf.generateContent(sendMailRequestDTO.getMailTemplateId(), map);
            MailSendResult mailSendResult = defaultMailClient.sendHtmlMail(host, port, username, password, mailTemplateResponseDTO.getPersonal(),
                    sendMailRequestDTO.getToAddress(), mailTemplateResponseDTO.getSubject(), mailText);
            return mailSendResult;
        }
    }
}