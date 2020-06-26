package com.wondernect.stars.file.service.astract;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.file.client.util.FileUploadResult;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.common.error.FileErrorEnum;
import com.wondernect.stars.file.common.exception.FileException;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import com.wondernect.stars.file.em.FileType;
import com.wondernect.stars.file.model.File;
import com.wondernect.stars.file.service.InitFileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AbstractFileService
 * Author: chenxun
 * Date: 2020-06-26 09:14
 * Description:
 */
public abstract class AbstractFileService extends BaseStringService<FileResponseDTO, File> implements InitFileService {

    public FileResponseDTO upload(MultipartFile fileMedia, String subFilePath, String fileType, Map<String, String> fileMetaData) {
        if (ESStringUtils.isRealEmpty(fileType)) {
            throw new FileException(FileErrorEnum.FILE_TYPE_IS_NULL);
        }
        FileType fileTypeEnum;
        try {
            fileTypeEnum = FileType.valueOf(fileType);
        } catch (IllegalArgumentException e) {
            throw new FileException(FileErrorEnum.FILE_TYPE_INVALID);
        }
        FileUploadResult fileUploadResult = uploadFile(fileTypeEnum, subFilePath, fileMedia, fileMetaData);
        if (!fileUploadResult.getResult()) {
            throw new FileException(FileErrorEnum.FILE_UPLOAD_FAILED, fileUploadResult.getMessage());
        }
        return saveFile(fileTypeEnum, subFilePath, fileUploadResult);
    }

    public abstract FileUploadResult uploadFile(FileType fileType, String subFilePath, MultipartFile fileMedia, Map<String, String> fileMetaData);

    public abstract FileResponseDTO saveFile(FileType fileType, String subFilePath, FileUploadResult fileUploadResult);

    @Override
    public void deleteById(String id) {
        File file = super.findEntityById(id);
        if (ESObjectUtils.isNotNull(file) && !file.getDeleted()) {
            deleteByFilePath(file.getLocalPath());
            file.setDeleted(true);
            super.save(file);
        }
    }

    public abstract void deleteByFilePath(String localPath);

    public List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO) {
        Criteria<File> fileCriteria = new Criteria<>();
        fileCriteria.add(Restrictions.eq("createUser", listFileRequestDTO.getUserId()));
        fileCriteria.add(Restrictions.eq("deleted", listFileRequestDTO.getDeleted()));
        return super.findAll(fileCriteria, listFileRequestDTO.getSortDataList());
    }

    public PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO) {
        Criteria<File> fileCriteria = new Criteria<>();
        fileCriteria.add(Restrictions.eq("createUser", pageFileRequestDTO.getUserId()));
        fileCriteria.add(Restrictions.eq("deleted", pageFileRequestDTO.getDeleted()));
        return super.findAll(fileCriteria, pageFileRequestDTO.getPageRequestData());
    }

    public FileResponseDTO generate(File file) {
        FileResponseDTO fileResponseDTO = new FileResponseDTO();
        ESBeanUtils.copyProperties(file, fileResponseDTO);
        fileResponseDTO.setId(file.getId());
        String imageThumbUrl = null;
        if (file.getType() == FileType.IMAGE) {
            imageThumbUrl = getImageThumbUrl(file.getThumbImagePath(), file.getSubFilePath());
        }
        fileResponseDTO.setThumbPath(imageThumbUrl);
        if (!file.getDeleted()) {
            fileResponseDTO.setPath(getFileDownloadUrl(file.getLocalPath(), file.getSubFilePath()));
        } else {
            fileResponseDTO.setLocalPath(null);
        }
        fileResponseDTO.setUploadType(file.getUploadType());
        fileResponseDTO.setType(file.getType());
        return fileResponseDTO;
    }

    public abstract String getImageThumbUrl(String thumbImagePath, String subFilePath);

    public abstract String getFileDownloadUrl(String localPath, String subFilePath);
}
