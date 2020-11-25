package com.wondernect.stars.mail.server.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.server.dto.ListMailServerRequestDTO;
import com.wondernect.stars.mail.server.dto.MailServerResponseDTO;
import com.wondernect.stars.mail.server.dto.PageMailServerRequestDTO;
import com.wondernect.stars.mail.server.dto.SaveMailServerRequestDTO;

import java.util.List;

/**
 * 邮箱服务器服务接口类
 *
 * @author 王威 2020-11-23 15:55:47
 **/
public interface MailServerInterface {

    /**
     * 创建
     **/
    MailServerResponseDTO create(SaveMailServerRequestDTO saveMailServerRequestDTO);

    /**
     * 更新
     **/
    MailServerResponseDTO update(String id, SaveMailServerRequestDTO saveMailServerRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    MailServerResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<MailServerResponseDTO> list(ListMailServerRequestDTO listMailServerRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<MailServerResponseDTO> page(PageMailServerRequestDTO pageMailServerRequestDTO);
}