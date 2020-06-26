package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
@ApiModel(value = "本地文件路径创建请求对象")
public class SaveLocalFilePathRequestDTO {

    @NotBlank(message = "名称不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(notes = "说明")
    private String description;

    @NotBlank(message = "路径不能为空")
    @JsonProperty("path")
    @ApiModelProperty(notes = "路径")
    private String path;

    @NotBlank(message = "父级路径不能为空")
    @JsonProperty("parent_path_id")
    @ApiModelProperty(notes = "父级路径id")
    private String parentPathId;
}
