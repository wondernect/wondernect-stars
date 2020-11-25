package com.wondernect.stars.mail.mail.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.mail.dto.ListMailRequestDTO;
import com.wondernect.stars.mail.mail.dto.MailResponseDTO;
import com.wondernect.stars.mail.mail.dto.PageMailRequestDTO;
import com.wondernect.stars.mail.mail.dto.SaveMailRequestDTO;

import java.util.List;

/**
 * 邮件服务接口类
 *
 * @author 王威 2020-11-23 11:21:48
 **/
public interface MailInterface {

    /**
     * 创建
     **/
    MailResponseDTO create(SaveMailRequestDTO saveMailRequestDTO);

    /**
     * 更新
     **/
    MailResponseDTO update(String id, SaveMailRequestDTO saveMailRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    MailResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<MailResponseDTO> list(ListMailRequestDTO listMailRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<MailResponseDTO> page(PageMailRequestDTO pageMailRequestDTO);
}