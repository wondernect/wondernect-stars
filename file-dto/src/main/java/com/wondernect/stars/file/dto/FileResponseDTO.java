package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import com.wondernect.stars.file.em.FileType;
import com.wondernect.stars.file.em.FileUploadType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FileResponseDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文件响应对象")
public class FileResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("upload_type")
    @ApiModelProperty(notes = "文件上传类型")
    private FileUploadType uploadType;

    @JsonProperty("type")
    @ApiModelProperty(notes = "文件类型")
    private FileType type;

    @JsonProperty("name")
    @ApiModelProperty(notes = "文件名称")
    private String name;

    @JsonProperty("size")
    @ApiModelProperty(notes = "文件大小")
    private Long size;

    @JsonProperty("ext")
    @ApiModelProperty(notes = "文件扩展名")
    private String ext;

    @JsonProperty("local_path_id")
    @ApiModelProperty(notes = "文件存储路径id")
    private String localPathId;

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

    @JsonProperty("deleted")
    @ApiModelProperty(notes = "文件是否已删除")
    private Boolean deleted;
}
