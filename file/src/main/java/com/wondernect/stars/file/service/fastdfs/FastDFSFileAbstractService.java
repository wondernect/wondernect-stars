package com.wondernect.stars.file.service.fastdfs;

import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.file.client.FastDFSFileClient;
import com.wondernect.elements.file.client.util.FileUploadResult;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.em.FileType;
import com.wondernect.stars.file.em.FileUploadType;
import com.wondernect.stars.file.model.File;
import com.wondernect.stars.file.service.base.FileAbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FastDFSFileService
 * Author: chenxun
 * Date: 2019/1/14 18:57
 * Description: fast dfs file service
 */
public abstract class FastDFSFileAbstractService extends FileAbstractService {

    private static final String FAST_DFS_SUB_FILE_PATH = "";

    @Autowired
    private FastDFSFileClient fastDFSFileClient;

    @Override
    public FileUploadResult uploadFile(FileType fileType, String subFilePath, MultipartFile fileMedia, Map<String, String> fileMetaData) {
        FileUploadResult fileUploadResult;
        if (fileType == FileType.IMAGE) {
            fileUploadResult = fastDFSFileClient.uploadImageAndCreateThumbImage(fileMedia, fileMetaData);
        } else {
            fileUploadResult = fastDFSFileClient.uploadFile(fileMedia, fileMetaData);
        }
        return fileUploadResult;
    }

    @Override
    public FileResponseDTO saveFile(FileType fileType, String subFilePath, FileUploadResult fileUploadResult) {
        return super.save(
                new File(
                        FileUploadType.FASTDFS,
                        fileType,
                        fileUploadResult.getFileName(),
                        fileUploadResult.getFileSize(),
                        fileUploadResult.getFileExt(),
                        FAST_DFS_SUB_FILE_PATH,
                        fileUploadResult.getFilePath(),
                        fileUploadResult.getThumbFilePath(),
                        ESJSONObjectUtils.jsonObjectToJsonString(fileUploadResult.getMetaData()),
                        false
                )
        );
    }

    @Override
    public void deleteByFilePath(String localPath) {
        fastDFSFileClient.deleteByFilePath(localPath);
    }

    @Override
    public String getImageThumbUrl(String thumbImagePath, String subFilePath) {
        return fastDFSFileClient.getImageThumbUrl(thumbImagePath);
    }

    @Override
    public String getFileDownloadUrl(String localPath, String subFilePath) {
        return fastDFSFileClient.getFileDownloadUrl(localPath);
    }
}
