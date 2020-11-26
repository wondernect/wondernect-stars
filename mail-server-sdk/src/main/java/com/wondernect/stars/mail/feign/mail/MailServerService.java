package com.wondernect.stars.mail.feign.mail;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.mail.ListMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.MailResponseDTO;
import com.wondernect.stars.mail.dto.mail.PageMailRequestDTO;
import com.wondernect.stars.mail.dto.mail.SaveMailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/11/26 11:41
 * @Version 1.0
 */

@Service
public class MailServerService {

    @Autowired
    private MailFeignClient mailFeignClient;

    public MailResponseDTO create(SaveMailRequestDTO saveMailRequestDTO){
        BusinessData<MailResponseDTO> businessData = mailFeignClient.create(saveMailRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public MailResponseDTO update(String id,SaveMailRequestDTO saveMailRequestDTO){
        BusinessData<MailResponseDTO> businessData = mailFeignClient.update(id,saveMailRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = mailFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.success();
    }

    public MailResponseDTO detail(String id){
        BusinessData<MailResponseDTO> businessData = mailFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<MailResponseDTO> list(ListMailRequestDTO listMailRequestDTO){
        BusinessData<List<MailResponseDTO>> businessData = mailFeignClient.list(listMailRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<MailResponseDTO> page(PageMailRequestDTO pageMailRequestDTO){
        BusinessData<PageResponseData<MailResponseDTO>> businessData = mailFeignClient.page(pageMailRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
