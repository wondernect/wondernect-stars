package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@ApiModel(value = "本地文件路径响应对象")
public class LocalFilePathResponseDTO {

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
}
