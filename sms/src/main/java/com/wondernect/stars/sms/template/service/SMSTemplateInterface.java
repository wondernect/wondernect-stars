package com.wondernect.stars.sms.template.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.template.dto.ListSMSTemplateRequestDTO;
import com.wondernect.stars.sms.template.dto.PageSMSTemplateRequestDTO;
import com.wondernect.stars.sms.template.dto.SMSTemplateResponseDTO;
import com.wondernect.stars.sms.template.dto.SaveSMSTemplateRequestDTO;

import java.util.List;

/**
 * 短信模板服务接口类
 *
 * @author chenxun 2020-12-27 11:54:09
 **/
public interface SMSTemplateInterface {

    /**
     * 创建
     **/
    SMSTemplateResponseDTO create(SaveSMSTemplateRequestDTO saveSMSTemplateRequestDTO);

    /**
     * 更新
     **/
    SMSTemplateResponseDTO update(String id, SaveSMSTemplateRequestDTO saveSMSTemplateRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    SMSTemplateResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<SMSTemplateResponseDTO> list(ListSMSTemplateRequestDTO listSMSTemplateRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<SMSTemplateResponseDTO> page(PageSMSTemplateRequestDTO pageSMSTemplateRequestDTO);
}