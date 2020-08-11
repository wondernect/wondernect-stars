package com.wondernect.stars.session.feign.captchaSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.captcha.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class MenuServerService {

    @Autowired
    private CaptchaSessionClient captchaSessionClient;

    public CaptchaResponseDTO request(CaptchaRequestDTO captchaRequestDTO){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionClient.request(captchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String captchaSessionId) {
        BusinessData businessData = captchaSessionClient.delete(captchaSessionId);
        return businessData.success();
    }

    public CaptchaResponseDTO detail(String captchaSessionId){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionClient.detail(captchaSessionId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean deleteCache(String captchaSessionId) {
        BusinessData businessData = captchaSessionClient.deleteCache(captchaSessionId);
        return businessData.success();
    }

    public CaptchaResponseDTO detailCache(String captchaSessionId){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionClient.detailCache(captchaSessionId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public CaptchaResponseDTO authCache(CaptchaAuthRequestDTO captchaAuthRequestDTO){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionClient.authCache(captchaAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<CaptchaResponseDTO> list(ListCaptchaRequestDTO listCaptchaRequestDTO){
        BusinessData<List<CaptchaResponseDTO>> businessData = captchaSessionClient.list(listCaptchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<CaptchaResponseDTO> page(PageCaptchaRequestDTO pageCaptchaRequestDTO){
        BusinessData<PageResponseData<CaptchaResponseDTO>> businessData = captchaSessionClient.page(pageCaptchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
