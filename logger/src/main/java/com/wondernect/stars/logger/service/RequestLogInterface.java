package com.wondernect.stars.logger.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.logger.dto.ListRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.PageRequestLogRequestDTO;
import com.wondernect.stars.logger.dto.RequestLogResponseDTO;
import com.wondernect.stars.logger.dto.SaveRequestLogRequestDTO;

import java.util.List;

/**
 * 日志服务接口类
 *
 * @author chenxun 2020-12-17 14:58:51
 **/
public interface RequestLogInterface {

    /**
     * 创建
     **/
    RequestLogResponseDTO create(SaveRequestLogRequestDTO saveRequestLogRequestDTO);

    /**
     * 更新
     **/
    RequestLogResponseDTO update(String id, SaveRequestLogRequestDTO saveRequestLogRequestDTO);

    /**
     * 删除
     **/
    void deleteById(String id);

    /**
     * 获取详细信息
     **/
    RequestLogResponseDTO findById(String id);

    /**
     * 列表
     **/
    List<RequestLogResponseDTO> list(ListRequestLogRequestDTO listRequestLogRequestDTO);

    /**
     * 分页
     **/
    PageResponseData<RequestLogResponseDTO> page(PageRequestLogRequestDTO pageRequestLogRequestDTO);
}