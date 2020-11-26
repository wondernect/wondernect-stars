package com.wondernect.stars.mail.feign.param;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.mail.ListMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.MailResponseDTO;
import com.wondernect.stars.mail.dto.mail.PageMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.SaveMailRequestDTO;
import com.wondernect.stars.mail.dto.param.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.dto.param.PageMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.SaveMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.feign.mail.MailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/11/26 11:41
 * @Version 1.0
 */

@Service
public class MailTemplateParamServerService {

    @Autowired
    private MailTemplateParamFeignClient mailTemplateParamFeignClient;

    public MailTemplateParamResponseDTO create(SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO){
        BusinessData<MailTemplateParamResponseDTO> businessData = mailTemplateParamFeignClient.create(saveMailTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public MailTemplateParamResponseDTO update(String id,SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO){
        BusinessData<MailTemplateParamResponseDTO> businessData = mailTemplateParamFeignClient.update(id,saveMailTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = mailTemplateParamFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public MailTemplateParamResponseDTO detail(String id){
        BusinessData<MailTemplateParamResponseDTO> businessData = mailTemplateParamFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<MailTemplateParamResponseDTO> list(ListMailTemplateParamRequestDTO listMailTemplateParamRequestDTO){
        BusinessData<List<MailTemplateParamResponseDTO>> businessData = mailTemplateParamFeignClient.list(listMailTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<MailTemplateParamResponseDTO> page(PageMailTemplateParamRequestDTO pageMailTemplateParamRequestDTO){
        BusinessData<PageResponseData<MailTemplateParamResponseDTO>> businessData = mailTemplateParamFeignClient.page(pageMailTemplateParamRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
