package com.wondernect.stars.mail.feign.server;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.mail.ListMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.PageMailRequestDTO;
import com.wondernect.stars.mail.dto.server.ListMailServerRequestDTO;
import com.wondernect.stars.mail.dto.server.MailServerResponseDTO;
import com.wondernect.stars.mail.dto.server.PageMailServerRequestDTO;
import com.wondernect.stars.mail.dto.server.SaveMailServerRequestDTO;
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
public class MailServerServerService {

    @Autowired
    private MailServerFeignClient mailServerFeignClient;

    public MailServerResponseDTO create(SaveMailServerRequestDTO saveMailServerRequestDTO){
        BusinessData<MailServerResponseDTO> businessData = mailServerFeignClient.create(saveMailServerRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public MailServerResponseDTO update(String id,SaveMailServerRequestDTO saveMailServerRequestDTO){
        BusinessData<MailServerResponseDTO> businessData = mailServerFeignClient.update(id,saveMailServerRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = mailServerFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public MailServerResponseDTO detail(String id){
        BusinessData<MailServerResponseDTO> businessData = mailServerFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<MailServerResponseDTO> list(ListMailServerRequestDTO listMailServerRequestDTO){
        BusinessData<List<MailServerResponseDTO>> businessData = mailServerFeignClient.list(listMailServerRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<MailServerResponseDTO> page(PageMailServerRequestDTO pageMailServerRequestDTO){
        BusinessData<PageResponseData<MailServerResponseDTO>> businessData = mailServerFeignClient.page(pageMailServerRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
