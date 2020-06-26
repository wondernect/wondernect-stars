package com.wondernect.stars.file.service.astract;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.file.client.LocalFileClient;
import com.wondernect.elements.file.client.util.FileUploadResult;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.em.FileType;
import com.wondernect.stars.file.em.FileUploadType;
import com.wondernect.stars.file.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: LocalFileService
 * Author: chenxun
 * Date: 2019/3/28 15:03
 * Description: default local file service
 */
public abstract class AbstractLocalFileService extends AbstractFileService {

    @Autowired
    private LocalFileClient localFileClient;

    @Override
    public FileUploadResult uploadFile(FileType fileType, String subFilePath, MultipartFile fileMedia, Map<String, String> fileMetaData) {
        FileUploadResult fileUploadResult;
        if (fileType == FileType.IMAGE) {
            fileUploadResult = localFileClient.uploadImageAndCreateThumbImage(fileMedia, subFilePath, fileMetaData);
        } else {
            fileUploadResult = localFileClient.uploadFile(fileMedia, subFilePath, fileMetaData);
        }
        return fileUploadResult;
    }

    @Override
    public FileResponseDTO saveFile(FileType fileType, String subFilePath, FileUploadResult fileUploadResult) {
        return super.save(
                new File(
                        FileUploadType.LOCAL,
                        fileType,
                        fileUploadResult.getFileName(),
                        fileUploadResult.getFileSize(),
                        fileUploadResult.getFileExt(),
                        subFilePath,
                        fileUploadResult.getFilePath(),
                        fileUploadResult.getThumbFilePath(),
                        ESJSONObjectUtils.jsonObjectToJsonString(fileUploadResult.getMetaData()),
                        false
                )
        );
    }

    @Override
    public void deleteByFilePath(String localPath) {
        localFileClient.deleteByFilePath(localPath);
    }

    @Override
    public String getImageThumbUrl(String thumbImagePath, String subFilePath) {
        return localFileClient.getImageThumbUrl(thumbImagePath, subFilePath);
    }

    @Override
    public String getFileDownloadUrl(String localPath, String subFilePath) {
        return localFileClient.getFileDownloadUrl(localPath, subFilePath);
    }
}
