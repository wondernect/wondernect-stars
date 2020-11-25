package com.wondernect.stars.mail.param.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.param.dto.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.param.dto.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.param.dto.PageMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.param.dto.SaveMailTemplateParamRequestDTO;

import java.util.List;

/**
 * 邮件模板参数服务接口类
 *
 * @author 王威 2020-11-23 15:55:10
 **/
public interface MailTemplateParamInterface {

    /**
     * 创建
     **/
    MailTemplateParamResponseDTO create(SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO);

    /**
     * 更新
     **/
    MailTemplateParamResponseDTO update(String id, SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    MailTemplateParamResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<MailTemplateParamResponseDTO> list(ListMailTemplateParamRequestDTO listMailTemplateParamRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<MailTemplateParamResponseDTO> page(PageMailTemplateParamRequestDTO pageMailTemplateParamRequestDTO);
}