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
@ApiModel(value = "文件响应对象")
public class FileResponseDTO {

    @JsonProperty("id")
    @ApiModelProperty(notes = "唯一标识")
    private String id;

    @JsonProperty("upload_type")
    @ApiModelProperty(notes = "文件上传类型")
    private String uploadType;

    @JsonProperty("type")
    @ApiModelProperty(notes = "文件类型")
    private String type;

    @JsonProperty("name")
    @ApiModelProperty(notes = "文件名称")
    private String name;

    @JsonProperty("size")
    @ApiModelProperty(notes = "文件大小")
    private Long size;

    @JsonProperty("ext")
    @ApiModelProperty(notes = "文件扩展名")
    private String ext;

    @JsonProperty("local_path")
    @ApiModelProperty(notes = "文件资源服务器唯一路径")
    private String localPath;

    @JsonProperty("path")
    @ApiModelProperty(notes = "访问url路径")
    private String path;

    @JsonProperty("thumb_image_path")
    @ApiModelProperty(notes = "图片文件缩略图访问服务器路径")
    private String thumbImagePath;

    @JsonProperty("thumb_path")
    @ApiModelProperty(notes = "缩略图访问url路径")
    private String thumbPath;

    @JsonProperty("meta_data")
    @ApiModelProperty(notes = "文件元信息")
    private String metaData;
}
