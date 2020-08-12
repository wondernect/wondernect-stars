package com.wondernect.stars.session.feign.codeSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.captcha.*;
import com.wondernect.stars.session.dto.code.*;
import com.wondernect.stars.session.feign.captchaSession.CaptchaSessionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class CodeSessionService {

    @Autowired
    private CodeSessionClient codeSessionClient;

    public CodeResponseDTO request(CodeRequestDTO codeRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionClient.request(codeRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String code) {
        BusinessData businessData = codeSessionClient.delete(code);
        return businessData.success();
    }

    public CodeResponseDTO detail(String code){
        BusinessData<CodeResponseDTO> businessData = codeSessionClient.detail(code);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean deleteCache(String code) {
        BusinessData businessData = codeSessionClient.deleteCache(code);
        return businessData.success();
    }

    public CodeResponseDTO detailCache(String code){
        BusinessData<CodeResponseDTO> businessData = codeSessionClient.detailCache(code);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public CodeResponseDTO refresh(CodeRefreshRequestDTO codeRefreshRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionClient.refresh(codeRefreshRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public CodeResponseDTO authCache(CodeAuthRequestDTO codeAuthRequestDTO){
        BusinessData<CodeResponseDTO> businessData = codeSessionClient.authCache(codeAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<CodeResponseDTO> list(ListCodeRequestDTO listCodeRequestDTO){
        BusinessData<List<CodeResponseDTO>> businessData = codeSessionClient.list(listCodeRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<CodeResponseDTO> page(PageCodeRequestDTO pageCodeRequestDTO){
        BusinessData<PageResponseData<CodeResponseDTO>> businessData = codeSessionClient.page(pageCodeRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
