package com.wondernect.stars.file.service;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import com.wondernect.stars.file.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FastDFSFileService
 * Author: chenxun
 * Date: 2019/1/14 17:41
 * Description: wondernect file service
 */
public interface InitFileService {

    /**
     * 上传文件（fileType为IMAGE时默认上传图片文件并生成图片缩略图）
     */
    FileResponseDTO upload(MultipartFile fileMedia, String fileType, Map<String, String> fileMetaData);

    /**
     * 删除文件
     */
    void deleteById(String id);

    /**
     * 获取文件信息
     */
    FileResponseDTO findById(String id);

    /**
     * 列表查询文件
     */
    List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO);

    /**
     * 分页查询文件
     */
    PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO);

    /**
     * 构造文件响应对象
     */
    FileResponseDTO generate(File file);
}
