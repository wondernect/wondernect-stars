package com.wondernect.stars.sms.sms.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.sms.sms.dto.ListSMSRequestDTO;
import com.wondernect.stars.sms.sms.dto.PageSMSRequestDTO;
import com.wondernect.stars.sms.sms.dto.SMSResponseDTO;
import com.wondernect.stars.sms.sms.dto.SaveSMSRequestDTO;

import java.util.List;

/**
 * 短信服务接口类
 *
 * @author chenxun 2020-12-27 11:53:24
 **/
public interface SMSInterface {

    /**
     * 创建
     **/
    SMSResponseDTO create(SaveSMSRequestDTO saveSMSRequestDTO);

    /**
     * 更新
     **/
    SMSResponseDTO update(String id, SaveSMSRequestDTO saveSMSRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    SMSResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<SMSResponseDTO> list(ListSMSRequestDTO listSMSRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<SMSResponseDTO> page(PageSMSRequestDTO pageSMSRequestDTO);
}