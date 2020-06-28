package com.wondernect.stars.file.service.base;

import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: InitFileService
 * Author: chenxun
 * Date: 2019/1/14 17:41
 * Description: wondernect file service
 */
public interface FileInterface {

    /**
     * 上传文件（fileType为IMAGE时默认上传图片文件并生成图片缩略图）
     */
    FileResponseDTO upload(MultipartFile fileMedia, String subFilePath, String fileType, Map<String, String> fileMetaData);

    /**
     * 删除
     */
    void deleteById(String id);

    /**
     * 获取
     */
    FileResponseDTO findById(String id);

    /**
     * 列表
     */
    List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO);

    /**
     * 分页
     */
    PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO);
}
