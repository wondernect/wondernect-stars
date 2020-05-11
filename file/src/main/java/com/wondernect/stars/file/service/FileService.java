package com.wondernect.stars.file.service;

import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
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
public interface FileService {

    /**
     * 上传文件（fileType为IMAGE时默认上传图片文件并生成图片缩略图）
     */
    File upload(MultipartFile fileMedia, String fileType, Map<String, String> fileMetaData);

    /**
     * 删除文件
     */
    void delete(String fileId);

    /**
     * 获取文件信息
     */
    File findByFileId(String fileId);

    /**
     * 列表查询文件
     */
    List<File> findAllByUserId(String userId, List<SortData> sortDataList);

    /**
     * 分页查询文件
     */
    PageResponseData<File> findAllByUserId(String userId, PageRequestData pageRequestData);
}
