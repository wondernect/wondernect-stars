package com.wondernect.stars.session.feign.tokenSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.code.*;
import com.wondernect.stars.session.dto.token.*;
import com.wondernect.stars.session.feign.codeSession.CodeSessionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class TokenSessionService {

    @Autowired
    private TokenSessionClient tokenSessionClient;

    public TokenResponseDTO request(TokenRequestDTO tokenRequestDTO){
        BusinessData<TokenResponseDTO> businessData = tokenSessionClient.request(tokenRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String token) {
        BusinessData businessData = tokenSessionClient.delete(token);
        return businessData.success();
    }

    public TokenResponseDTO get(String token){
        BusinessData<TokenResponseDTO> businessData = tokenSessionClient.get(token);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public TokenResponseDTO refresh(TokenRefreshRequestDTO tokenRefreshRequestDTO) {
        BusinessData<TokenResponseDTO> businessData = tokenSessionClient.refresh(tokenRefreshRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public TokenResponseDTO auth(TokenAuthRequestDTO tokenAuthRequestDTO){
        BusinessData<TokenResponseDTO> businessData = tokenSessionClient.auth(tokenAuthRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<TokenResponseDTO> list(ListTokenRequestDTO listTokenRequestDTO){
        BusinessData<List<TokenResponseDTO>> businessData = tokenSessionClient.list(listTokenRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<TokenResponseDTO> page(PageTokenRequestDTO pageTokenRequestDTO){
        BusinessData<PageResponseData<TokenResponseDTO>> businessData = tokenSessionClient.page(pageTokenRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
