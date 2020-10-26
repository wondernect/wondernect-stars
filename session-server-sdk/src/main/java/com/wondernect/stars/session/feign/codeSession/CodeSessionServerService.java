package com.wondernect.stars.session.feign.codeSession;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.code.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class CodeSessionServerService {

    @Autowired
    private CodeSessionFeignClient codeSessionFeignClient;

    public CodeResponseDTO request(CodeRequestDTO codeRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionFeignClient.request(codeRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String code) {
        BusinessData businessData = codeSessionFeignClient.delete(code);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public CodeResponseDTO detail(String code){
        BusinessData<CodeResponseDTO> businessData = codeSessionFeignClient.detail(code);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void deleteCache(String code) {
        BusinessData businessData = codeSessionFeignClient.deleteCache(code);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public CodeResponseDTO detailCache(String code){
        BusinessData<CodeResponseDTO> businessData = codeSessionFeignClient.detailCache(code);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public CodeResponseDTO refresh(CodeRefreshRequestDTO codeRefreshRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionFeignClient.refresh(codeRefreshRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public CodeResponseDTO authCache(CodeAuthRequestDTO codeAuthRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionFeignClient.authCache(codeAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<CodeResponseDTO> list(ListCodeRequestDTO listCodeRequestDTO){
        BusinessData<List<CodeResponseDTO>> businessData = codeSessionFeignClient.list(listCodeRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<CodeResponseDTO> page(PageCodeRequestDTO pageCodeRequestDTO){
        BusinessData<PageResponseData<CodeResponseDTO>> businessData = codeSessionFeignClient.page(pageCodeRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
