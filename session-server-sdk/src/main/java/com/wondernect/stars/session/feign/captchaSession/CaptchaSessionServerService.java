package com.wondernect.stars.session.feign.captchaSession;

import com.wondernect.elements.common.exception.BusinessException;
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
public class CaptchaSessionServerService {

    @Autowired
    private CaptchaSessionFeignClient captchaSessionFeignClient;

    public CaptchaResponseDTO request(CaptchaRequestDTO captchaRequestDTO){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionFeignClient.request(captchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public void delete(String captchaSessionId) {
        BusinessData businessData = captchaSessionFeignClient.delete(captchaSessionId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public CaptchaResponseDTO detail(String captchaSessionId){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionFeignClient.detail(captchaSessionId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public void deleteCache(String captchaSessionId) {
        BusinessData businessData = captchaSessionFeignClient.deleteCache(captchaSessionId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public CaptchaResponseDTO detailCache(String captchaSessionId){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionFeignClient.detailCache(captchaSessionId);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public CaptchaResponseDTO authCache(CaptchaAuthRequestDTO captchaAuthRequestDTO){
        BusinessData<CaptchaResponseDTO> businessData = captchaSessionFeignClient.authCache(captchaAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<CaptchaResponseDTO> list(ListCaptchaRequestDTO listCaptchaRequestDTO){
        BusinessData<List<CaptchaResponseDTO>> businessData = captchaSessionFeignClient.list(listCaptchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<CaptchaResponseDTO> page(PageCaptchaRequestDTO pageCaptchaRequestDTO){
        BusinessData<PageResponseData<CaptchaResponseDTO>> businessData = captchaSessionFeignClient.page(pageCaptchaRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
