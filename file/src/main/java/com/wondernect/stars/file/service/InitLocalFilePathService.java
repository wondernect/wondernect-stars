package com.wondernect.stars.file.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.ListLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.LocalFilePathResponseDTO;
import com.wondernect.stars.file.dto.PageLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.SaveLocalFilePathRequestDTO;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitLocalFilePathService
 * Author: chenxun
 * Date: 2020-06-26 19:02
 * Description:
 */
public interface InitLocalFilePathService {

    /**
     * 创建
     */
    LocalFilePathResponseDTO create(SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO);

    /**
     * 获取
     */
    LocalFilePathResponseDTO findById(String id);

    /**
     * 列表
     */
    List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO);

    /**
     * 分页
     */
    PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO);
}
