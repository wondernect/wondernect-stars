package com.wondernect.stars.file.service.impl;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.file.client.FastDFSFileClient;
import com.wondernect.elements.file.client.LocalFileClient;
import com.wondernect.elements.file.client.util.FileUploadResult;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.common.error.FileErrorEnum;
import com.wondernect.stars.file.common.exception.FileException;
import com.wondernect.stars.file.manager.FileManager;
import com.wondernect.stars.file.model.File;
import com.wondernect.stars.file.model.em.FileType;
import com.wondernect.stars.file.model.em.FileUploadType;
import com.wondernect.stars.file.service.FileService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FastDFSFileService
 * Author: chenxun
 * Date: 2019/1/14 18:57
 * Description: fast dfs file service
 */
@Service(value = "fast_dfs")
public class FastDFSFileService implements FileService {

    @Autowired
    private LocalFileClient localFileClient;

    @Autowired
    private FastDFSFileClient fastDFSFileClient;

    @Autowired
    private FileManager fileManager;

    @Override
    public File upload(MultipartFile fileMedia, String fileType, Map<String, String> fileMetaData) {
        if (ESStringUtils.isRealEmpty(fileType)) {
            throw new FileException(FileErrorEnum.FILE_TYPE_IS_NULL);
        }
        FileType fileTypeEnum;
        try {
            fileTypeEnum = FileType.valueOf(fileType);
        } catch (IllegalArgumentException e) {
            throw new FileException(FileErrorEnum.FILE_TYPE_INVALID);
        }
        FileUploadResult fileUploadResult;
        if (fileTypeEnum == FileType.IMAGE) {
            fileUploadResult = fastDFSFileClient.uploadImageAndCreateThumbImage(fileMedia, fileMetaData);
        } else {
            fileUploadResult = fastDFSFileClient.uploadFile(fileMedia, fileMetaData);
        }
        if (!fileUploadResult.getResult()) {
            throw new FileException(FileErrorEnum.FILE_UPLOAD_FAILED, fileUploadResult.getMessage());
        }
        File file = fileManager.save(
                new File(
                        FileUploadType.FASTDFS,
                        fileTypeEnum,
                        fileUploadResult.getFileName(),
                        fileUploadResult.getFileSize(),
                        fileUploadResult.getFileExt(),
                        fileUploadResult.getFilePath(),
                        fastDFSFileClient.getFileDownloadUrl(fileUploadResult.getFilePath()),
                        fileUploadResult.getThumbFilePath(),
                        fastDFSFileClient.getImageThumbUrl(fileUploadResult.getThumbFilePath()),
                        ESJSONObjectUtils.jsonObjectToJsonString(fileUploadResult.getMetaData())
                )
        );
        return getFilePath(file);
    }

    @Override
    public void delete(String fileId) {
        File file = fileManager.findById(fileId);
        if (ESObjectUtils.isNotNull(file)) {
            fileManager.deleteById(fileId);
            switch (file.getUploadType()) {
                case LOCAL:
                {
                    localFileClient.deleteByFilePath(file.getLocalPath());
                    break;
                }
                case FASTDFS:
                {
                    fastDFSFileClient.deleteByFilePath(file.getLocalPath());
                    break;
                }
            }
        }
    }

    @Override
    public File findByFileId(String fileId) {
        File file = fileManager.findById(fileId);
        if (ESObjectUtils.isNull(file)) {
            throw new FileException(FileErrorEnum.FILE_NOT_FOUND);
        }
        return getFilePath(file);
    }

    @Override
    public List<File> findAllByUserId(String userId, List<SortData> sortDataList) {
        List<File> fileListResponse = new ArrayList<>();
        List<File> fileList;
        if (ESStringUtils.isNotBlank(userId)) {
            fileList = fileManager.findAllByCreateUser(userId, sortDataList);
        } else {
            fileList = fileManager.findAll(sortDataList);
        }
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (File file : fileList) {
                fileListResponse.add(getFilePath(file));
            }
        }
        return fileListResponse;
    }

    @Override
    public PageResponseData<File> findAllByUserId(String userId, PageRequestData pageRequestData) {
        PageResponseData<File> filePageResponseData;
        if (ESStringUtils.isNotBlank(userId)) {
            filePageResponseData = fileManager.findAllByCreateUser(userId, pageRequestData);
        } else {
            filePageResponseData = fileManager.findAll(pageRequestData);
        }
        List<File> fileResponseDTOList = new ArrayList<>();
        List<File> fileList = filePageResponseData.getDataList();
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (File file : fileList) {
                fileResponseDTOList.add(getFilePath(file));
            }
        }
        return new PageResponseData<>(filePageResponseData.getPage(), filePageResponseData.getSize(), filePageResponseData.getTotalPages(), filePageResponseData.getTotalElements(), fileResponseDTOList);
    }

    private File getFilePath(File file) {
        switch (file.getUploadType()) {
            case LOCAL:
            {
                String imageThumbUrl = null;
                if (file.getType() == FileType.IMAGE) {
                    imageThumbUrl = localFileClient.getImageThumbUrl(file.getThumbImagePath());
                }
                file.setThumbPath(imageThumbUrl);
                file.setPath(localFileClient.getFileDownloadUrl(file.getLocalPath()));
                break;
            }
            case FASTDFS:
            {
                String imageThumbUrl = null;
                if (file.getType() == FileType.IMAGE) {
                    imageThumbUrl = fastDFSFileClient.getImageThumbUrl(file.getLocalPath());
                }
                file.setThumbPath(imageThumbUrl);
                file.setPath(fastDFSFileClient.getFileDownloadUrl(file.getLocalPath()));
                break;
            }
        }
        return file;
    }
}
