package com.wondernect.stars.file.service.astract;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.ListLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.LocalFilePathResponseDTO;
import com.wondernect.stars.file.dto.PageLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.SaveLocalFilePathRequestDTO;
import com.wondernect.stars.file.model.LocalFilePath;
import com.wondernect.stars.file.service.InitLocalFilePathService;

import java.io.File;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AbstractLocalFilePathService
 * Author: chenxun
 * Date: 2020-06-26 19:22
 * Description:
 */
public abstract class AbstractLocalFilePathService extends BaseStringService<LocalFilePathResponseDTO, LocalFilePath> implements InitLocalFilePathService {

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
                        saveLocalFilePathRequestDTO.getParentPathId()
                )
        );
    }

    @Override
    public List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO) {
        Criteria<LocalFilePath> localFilePathCriteria = new Criteria<>();
        localFilePathCriteria.add(Restrictions.eq("parentPathId", listLocalFilePathRequestDTO.getParentPathId()));
        return super.findAll(localFilePathCriteria, listLocalFilePathRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO) {
        Criteria<LocalFilePath> localFilePathCriteria = new Criteria<>();
        localFilePathCriteria.add(Restrictions.eq("parentPathId", pageLocalFilePathRequestDTO.getParentPathId()));
        return super.findAll(localFilePathCriteria, pageLocalFilePathRequestDTO.getPageRequestData());
    }

    @Override
    public LocalFilePathResponseDTO generate(LocalFilePath localFilePath) {
        return new LocalFilePathResponseDTO(
                localFilePath.getId(),
                localFilePath.getName(),
                localFilePath.getDescription(),
                localFilePath.getPath(),
                localFilePath.getSubFilePath(),
                localFilePath.getParentPathId()
        );
    }
}
