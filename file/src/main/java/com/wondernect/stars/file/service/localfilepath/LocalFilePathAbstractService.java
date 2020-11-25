package com.wondernect.stars.file.service.localfilepath;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
import com.wondernect.stars.file.model.LocalFilePath;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AbstractLocalFilePathService
 * Author: chenxun
 * Date: 2020-06-26 19:22
 * Description:
 */
public abstract class LocalFilePathAbstractService extends BaseStringService<LocalFilePathResponseDTO, LocalFilePath> implements LocalFilePathInterface {

    @Override
    public LocalFilePathResponseDTO create(SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO) {
        LocalFilePath parentLocalFilePath = super.findEntityById(saveLocalFilePathRequestDTO.getParentPathId());
        if (ESObjectUtils.isNull(parentLocalFilePath)) {
            throw new BusinessException("父级路径不存在");
        }
        if (saveLocalFilePathRequestDTO.getPath().contains("/") ||
                saveLocalFilePathRequestDTO.getPath().contains("\\") ||
                saveLocalFilePathRequestDTO.getPath().contains("thumb")) {
            throw new BusinessException("路径不能包含特殊字符/或\\或thumb(路径必须为小写字母)");
        }
        String fullPath;
        if (ESStringUtils.isBlank(parentLocalFilePath.getSubFilePath())) {
            fullPath = saveLocalFilePathRequestDTO.getPath();
        } else {
            fullPath = parentLocalFilePath.getSubFilePath() + File.separator + saveLocalFilePathRequestDTO.getPath();
        }
        return super.save(
                new LocalFilePath(
                        saveLocalFilePathRequestDTO.getName(),
                        saveLocalFilePathRequestDTO.getDescription(),
                        saveLocalFilePathRequestDTO.getPath(),
                        fullPath,
                        saveLocalFilePathRequestDTO.getParentPathId(),
                        false
                )
        );
    }

    @Override
    public LocalFilePathResponseDTO update(String id, SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO) {
        LocalFilePath parentLocalFilePath = super.findEntityById(saveLocalFilePathRequestDTO.getParentPathId());
        if (ESObjectUtils.isNull(parentLocalFilePath)) {
            throw new BusinessException("父级文件路径不存在");
        }
        LocalFilePath localFilePath = super.findEntityById(id);
        if (ESObjectUtils.isNull(localFilePath)) {
            throw new BusinessException("文件路径不存在");
        }
        if (localFilePath.getIsDeleted()) {
            throw new BusinessException("文件路径已标记删除,不可更新");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveLocalFilePathRequestDTO, localFilePath);
        return super.save(localFilePath);
    }

    @Override
    public void deleteById(String id) {
        LocalFilePath localFilePath = super.findEntityById(id);
        if (ESObjectUtils.isNull(localFilePath)) {
            throw new BusinessException("文件路径不存在");
        }
        localFilePath.setIsDeleted(true);
        super.saveEntity(localFilePath);
    }

    @Override
    public List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO) {
        Criteria<LocalFilePath> localFilePathCriteria = new Criteria<>();
        localFilePathCriteria.add(Restrictions.eq("parentPathId", listLocalFilePathRequestDTO.getParentPathId()));
        localFilePathCriteria.add(Restrictions.eq("isDeleted", listLocalFilePathRequestDTO.getIsDeleted()));
        return super.findAll(localFilePathCriteria, listLocalFilePathRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO) {
        Criteria<LocalFilePath> localFilePathCriteria = new Criteria<>();
        localFilePathCriteria.add(Restrictions.eq("parentPathId", pageLocalFilePathRequestDTO.getParentPathId()));
        localFilePathCriteria.add(Restrictions.eq("isDeleted", pageLocalFilePathRequestDTO.getIsDeleted()));
        return super.findAll(localFilePathCriteria, pageLocalFilePathRequestDTO.getPageRequestData());
    }

    @Override
    public LocalFilePathTreeResponseDTO tree(String id) {
        LocalFilePath localFilePath = super.findEntityById(id);
        if (ESObjectUtils.isNull(localFilePath)) {
            throw new BusinessException("文件路径不存在");
        }
        if (localFilePath.getIsDeleted()) {
            throw new BusinessException("文件路径已标记删除");
        }
        LocalFilePath parentLocalFilePath = super.findEntityById(localFilePath.getParentPathId());
        if (ESObjectUtils.isNull(parentLocalFilePath)) {
            throw new BusinessException("父级文件路径不存在");
        }
        LocalFilePathTreeResponseDTO localFilePathTreeResponseDTO = new LocalFilePathTreeResponseDTO(
                localFilePath.getId(),
                localFilePath.getName(),
                localFilePath.getDescription(),
                localFilePath.getPath(),
                localFilePath.getSubFilePath(),
                localFilePath.getParentPathId(),
                parentLocalFilePath.getName(),
                parentLocalFilePath.getPath(),
                parentLocalFilePath.getSubFilePath(),
                localFilePath.getIsDeleted(),
                null
        );
        return tree(localFilePathTreeResponseDTO.getId(), localFilePathTreeResponseDTO);
    }

    private LocalFilePathTreeResponseDTO tree(String parentFilePathId, LocalFilePathTreeResponseDTO localFilePathTreeResponseDTO) {
        List<LocalFilePathTreeResponseDTO> localFilePathTreeResponseDTOList = new ArrayList<>();
        Criteria<LocalFilePath> localFilePathCriteria = new Criteria<>();
        localFilePathCriteria.add(Restrictions.eq("parentPathId", parentFilePathId));
        localFilePathCriteria.add(Restrictions.eq("isDeleted", false));
        List<LocalFilePath> localFilePathList = super.findAllEntity(localFilePathCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(localFilePathList)) {
            for (LocalFilePath localFilePath : localFilePathList) {
                LocalFilePath parentLocalFilePath = super.findEntityById(localFilePath.getParentPathId());
                localFilePathTreeResponseDTOList.add(
                        new LocalFilePathTreeResponseDTO(
                                localFilePath.getId(),
                                localFilePath.getName(),
                                localFilePath.getDescription(),
                                localFilePath.getPath(),
                                localFilePath.getSubFilePath(),
                                localFilePath.getParentPathId(),
                                parentLocalFilePath.getName(),
                                parentLocalFilePath.getPath(),
                                parentLocalFilePath.getSubFilePath(),
                                localFilePath.getIsDeleted(),
                                null
                                )
                );
            }
        }
        localFilePathTreeResponseDTO.setChildList(localFilePathTreeResponseDTOList);
        if (CollectionUtils.isNotEmpty(localFilePathTreeResponseDTOList)) {
            for (LocalFilePathTreeResponseDTO localFilePathTreeResponseDTOLoop : localFilePathTreeResponseDTOList) {
                tree(localFilePathTreeResponseDTOLoop.getId(), localFilePathTreeResponseDTOLoop);
            }
        }
        return localFilePathTreeResponseDTO;
    }

    @Override
    public LocalFilePathResponseDTO generate(LocalFilePath localFilePath) {
        LocalFilePathResponseDTO localFilePathResponseDTO = new LocalFilePathResponseDTO();
        ESBeanUtils.copyProperties(localFilePath, localFilePathResponseDTO);
        LocalFilePath parentLocalFilePath = super.findEntityById(localFilePath.getParentPathId());
        localFilePathResponseDTO.setParentPathName(ESObjectUtils.isNotNull(parentLocalFilePath) ? parentLocalFilePath.getName() : null);
        localFilePathResponseDTO.setParentPath(ESObjectUtils.isNotNull(parentLocalFilePath) ? parentLocalFilePath.getPath() : null);
        localFilePathResponseDTO.setParentSubFilePath(ESObjectUtils.isNotNull(parentLocalFilePath) ? parentLocalFilePath.getSubFilePath() : null);
        return localFilePathResponseDTO;
    }
}
