package com.wondernect.stars.session.feign.tokenSession;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.token.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class TokenSessionServerService {

    @Autowired
    private TokenSessionFeignClient tokenSessionFeignClient;

    public TokenResponseDTO request(TokenRequestDTO tokenRequestDTO){
        BusinessData<TokenResponseDTO> businessData = tokenSessionFeignClient.request(tokenRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String token) {
        BusinessData businessData = tokenSessionFeignClient.delete(token);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public TokenResponseDTO get(String token){
        BusinessData<TokenResponseDTO> businessData = tokenSessionFeignClient.get(token);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public TokenResponseDTO refresh(TokenRefreshRequestDTO tokenRefreshRequestDTO) {
        BusinessData<TokenResponseDTO> businessData = tokenSessionFeignClient.refresh(tokenRefreshRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public TokenResponseDTO auth(TokenAuthRequestDTO tokenAuthRequestDTO){
        BusinessData<TokenResponseDTO> businessData = tokenSessionFeignClient.auth(tokenAuthRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<TokenResponseDTO> list(ListTokenRequestDTO listTokenRequestDTO){
        BusinessData<List<TokenResponseDTO>> businessData = tokenSessionFeignClient.list(listTokenRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<TokenResponseDTO> page(PageTokenRequestDTO pageTokenRequestDTO){
        BusinessData<PageResponseData<TokenResponseDTO>> businessData = tokenSessionFeignClient.page(pageTokenRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
