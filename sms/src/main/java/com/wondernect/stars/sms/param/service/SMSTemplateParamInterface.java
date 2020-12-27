package com.wondernect.stars.sms.param.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.param.dto.ListSMSTemplateParamRequestDTO;
import com.wondernect.stars.sms.param.dto.PageSMSTemplateParamRequestDTO;
import com.wondernect.stars.sms.param.dto.SMSTemplateParamResponseDTO;
import com.wondernect.stars.sms.param.dto.SaveSMSTemplateParamRequestDTO;

import java.util.List;

/**
 * 短信模板变量服务接口类
 *
 * @author chenxun 2020-12-27 11:54:44
 **/
public interface SMSTemplateParamInterface {

    /**
     * 创建
     **/
    SMSTemplateParamResponseDTO create(SaveSMSTemplateParamRequestDTO saveSMSTemplateParamRequestDTO);

    /**
     * 更新
     **/
    SMSTemplateParamResponseDTO update(String id, SaveSMSTemplateParamRequestDTO saveSMSTemplateParamRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    SMSTemplateParamResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<SMSTemplateParamResponseDTO> list(ListSMSTemplateParamRequestDTO listSMSTemplateParamRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<SMSTemplateParamResponseDTO> page(PageSMSTemplateParamRequestDTO pageSMSTemplateParamRequestDTO);
}