package com.wondernect.stars.file.service.astract;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESJSONObjectUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.file.client.FastDFSFileClient;
import com.wondernect.elements.file.client.LocalFileClient;
import com.wondernect.elements.file.client.util.FileUploadResult;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.common.error.FileErrorEnum;
import com.wondernect.stars.file.common.exception.FileException;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import com.wondernect.stars.file.manager.FileManager;
import com.wondernect.stars.file.model.File;
import com.wondernect.stars.file.model.em.FileType;
import com.wondernect.stars.file.model.em.FileUploadType;
import com.wondernect.stars.file.service.InitFileService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AbstractFastDFSFileService implements InitFileService {

    @Autowired
    private LocalFileClient localFileClient;

    @Autowired
    private FastDFSFileClient fastDFSFileClient;

    @Autowired
    private FileManager fileManager;

    @Override
    public FileResponseDTO upload(MultipartFile fileMedia, String fileType, Map<String, String> fileMetaData) {
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
                        fileUploadResult.getThumbFilePath(),
                        ESJSONObjectUtils.jsonObjectToJsonString(fileUploadResult.getMetaData())
                )
        );
        return generate(file);
    }

    @Override
    public void deleteById(String id) {
        File file = fileManager.findById(id);
        if (ESObjectUtils.isNotNull(file)) {
            fileManager.deleteById(id);
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
    public FileResponseDTO findById(String id) {
        File file = fileManager.findById(id);
        if (ESObjectUtils.isNull(file)) {
            throw new FileException(FileErrorEnum.FILE_NOT_FOUND);
        }
        return generate(file);
    }

    @Override
    public List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO) {
        List<FileResponseDTO> fileResponseDTOList = new ArrayList<>();
        List<File> fileList;
        if (ESStringUtils.isNotBlank(listFileRequestDTO.getUserId())) {
            fileList = fileManager.findAllByCreateUser(listFileRequestDTO.getUserId(), listFileRequestDTO.getSortDataList());
        } else {
            fileList = fileManager.findAll(listFileRequestDTO.getSortDataList());
        }
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (File file : fileList) {
                fileResponseDTOList.add(generate(file));
            }
        }
        return fileResponseDTOList;
    }

    @Override
    public PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO) {
        PageResponseData<File> filePageResponseData;
        if (ESStringUtils.isNotBlank(pageFileRequestDTO.getUserId())) {
            filePageResponseData = fileManager.findAllByCreateUser(pageFileRequestDTO.getUserId(), pageFileRequestDTO.getPageRequestData());
        } else {
            filePageResponseData = fileManager.findAll(pageFileRequestDTO.getPageRequestData());
        }
        List<FileResponseDTO> fileResponseDTOList = new ArrayList<>();
        List<File> fileList = filePageResponseData.getDataList();
        if (CollectionUtils.isNotEmpty(fileList)) {
            for (File file : fileList) {
                fileResponseDTOList.add(generate(file));
            }
        }
        return new PageResponseData<>(filePageResponseData.getPage(), filePageResponseData.getSize(), filePageResponseData.getTotalPages(), filePageResponseData.getTotalElements(), fileResponseDTOList);
    }

    public FileResponseDTO generate(File file) {
        FileResponseDTO fileResponseDTO = new FileResponseDTO();
        ESBeanUtils.copyProperties(file, fileResponseDTO);
        fileResponseDTO.setId(file.getId());
        switch (file.getUploadType()) {
            case LOCAL:
            {
                String imageThumbUrl = null;
                if (file.getType() == FileType.IMAGE) {
                    imageThumbUrl = localFileClient.getImageThumbUrl(file.getThumbImagePath());
                }
                fileResponseDTO.setThumbPath(imageThumbUrl);
                fileResponseDTO.setPath(localFileClient.getFileDownloadUrl(file.getLocalPath()));
                break;
            }
            case FASTDFS:
            {
                String imageThumbUrl = null;
                if (file.getType() == FileType.IMAGE) {
                    imageThumbUrl = fastDFSFileClient.getImageThumbUrl(file.getThumbImagePath());
                }
                fileResponseDTO.setThumbPath(imageThumbUrl);
                fileResponseDTO.setPath(fastDFSFileClient.getFileDownloadUrl(file.getLocalPath()));
                break;
            }
        }
        fileResponseDTO.setUploadType(file.getUploadType().name());
        fileResponseDTO.setType(file.getType().name());
        return fileResponseDTO;
    }
}
