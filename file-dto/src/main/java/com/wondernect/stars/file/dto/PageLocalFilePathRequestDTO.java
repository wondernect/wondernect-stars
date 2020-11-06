package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: PageFileRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "本地文件路径分页请求对象")
public class PageLocalFilePathRequestDTO extends PageRequestDTO {

    @JsonProperty("parent_path_id")
    @ApiModelProperty(notes = "父级路径id")
    private String parentPathId;

    @JsonProperty("is_deleted")
    @ApiModelProperty(notes = "是否删除")
    private Boolean isDeleted = false;
}
