package com.wondernect.stars.mail.service.template;


import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.template.ListMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.MailTemplateResponseDTO;
import com.wondernect.stars.mail.dto.template.PageMailTemplateRequestDTO;
import com.wondernect.stars.mail.dto.template.SaveMailTemplateRequestDTO;

import java.util.List;

/**
 * 邮件模板服务接口类
 *
 * @author 王威 2020-11-23 15:56:11
 **/
public interface MailTemplateInterface {

    /**
     * 创建
     **/
    MailTemplateResponseDTO create(SaveMailTemplateRequestDTO saveMailTemplateRequestDTO);

    /**
     * 更新
     **/
    MailTemplateResponseDTO update(String id, SaveMailTemplateRequestDTO saveMailTemplateRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    MailTemplateResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<MailTemplateResponseDTO> list(ListMailTemplateRequestDTO listMailTemplateRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<MailTemplateResponseDTO> page(PageMailTemplateRequestDTO pageMailTemplateRequestDTO);
}