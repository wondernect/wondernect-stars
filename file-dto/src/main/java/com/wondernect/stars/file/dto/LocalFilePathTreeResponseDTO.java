package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FileResponseDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "本地文件路径树形响应对象")
public class LocalFilePathTreeResponseDTO {

    @JsonProperty("id")
    @ApiModelProperty(notes = "唯一标识")
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(notes = "说明")
    private String description;

    @JsonProperty("path")
    @ApiModelProperty(notes = "路径")
    private String path;

    @JsonProperty("sub_file_path")
    @ApiModelProperty(notes = "文件存储根目录下子目录")
    private String subFilePath;

    @JsonProperty("parent_path_id")
    @ApiModelProperty(notes = "父级路径id")
    private String parentPathId;

    @JsonProperty("parent_path_name")
    @ApiModelProperty(notes = "父级路径名称")
    private String parentPathName;

    @JsonProperty("parent_path")
    @ApiModelProperty(notes = "父级路径")
    private String parentPath;

    @JsonProperty("parent_sub_file_path")
    @ApiModelProperty(notes = "父级路径文件存储根目录下子目录")
    private String parentSubFilePath;

    @JsonProperty("is_deleted")
    @ApiModelProperty(notes = "是否删除")
    private Boolean isDeleted;

    @JsonProperty("child_list")
    @ApiModelProperty(notes = "子节点列表")
    private List<LocalFilePathTreeResponseDTO> childList;
}
