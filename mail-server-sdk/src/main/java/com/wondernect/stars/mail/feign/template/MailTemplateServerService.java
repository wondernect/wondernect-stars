package com.wondernect.stars.mail.feign.template;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.mail.ListMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.PageMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.SaveMailRequestDTO;
import com.wondernect.stars.mail.dto.template.ListMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.MailTemplateResponseDTO;
import com.wondernect.stars.mail.dto.template.PageMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.SaveMailTemplateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/11/26 11:41
 * @Version 1.0
 */

@Service
public class MailTemplateServerService {

    @Autowired
    private MailTemplateFeignClient mailTemplateFeignClient;

    public MailTemplateResponseDTO create(SaveMailTemplateRequestDTO saveMailTemplateRequestDTO){
        BusinessData<MailTemplateResponseDTO> businessData = mailTemplateFeignClient.create(saveMailTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public MailTemplateResponseDTO update(String id,SaveMailTemplateRequestDTO saveMailTemplateRequestDTO){
        BusinessData<MailTemplateResponseDTO> businessData = mailTemplateFeignClient.update(id,saveMailTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = mailTemplateFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public MailTemplateResponseDTO detail(String id){
        BusinessData<MailTemplateResponseDTO> businessData = mailTemplateFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<MailTemplateResponseDTO> list(ListMailTemplateRequestDTO listMailTemplateRequestDTO){
        BusinessData<List<MailTemplateResponseDTO>> businessData = mailTemplateFeignClient.list(listMailTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<MailTemplateResponseDTO> page(PageMailTemplateRequestDTO pageMailTemplateRequestDTO){
        BusinessData<PageResponseData<MailTemplateResponseDTO>> businessData = mailTemplateFeignClient.page(pageMailTemplateRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
