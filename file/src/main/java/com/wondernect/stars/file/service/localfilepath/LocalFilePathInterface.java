package com.wondernect.stars.file.service.localfilepath;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitLocalFilePathService
 * Author: chenxun
 * Date: 2020-06-26 19:02
 * Description:
 */
public interface LocalFilePathInterface {

    /**
     * 创建
     */
    LocalFilePathResponseDTO create(SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO);

    /**
     * 创建
     */
    LocalFilePathResponseDTO update(String id, SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO);

    /**
     * 获取
     */
    LocalFilePathResponseDTO findById(String id);

    /**
     * 删除
     */
    void deleteById(String id);

    /**
     * 列表
     */
    List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO);

    /**
     * 分页
     */
    PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO);

    /**
     * 树形结构
     */
    LocalFilePathTreeResponseDTO tree(String id);
}
