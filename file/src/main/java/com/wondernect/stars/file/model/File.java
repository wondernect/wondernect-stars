package com.wondernect.stars.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.file.em.FileType;
import com.wondernect.stars.file.em.FileUploadType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cxhome on 2017/8/23.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "file")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "文件")
public class File extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 6788863875446323944L;

    @Enumerated(EnumType.STRING)
    @JsonProperty("upload_type")
    @ApiModelProperty(notes = "文件上传类型")
    private FileUploadType uploadType;

    @Enumerated(EnumType.STRING)
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

    @JsonProperty("sub_file_path")
    @ApiModelProperty(notes = "文件存储根目录下子目录")
    private String subFilePath;

    @JsonProperty("local_path")
    @ApiModelProperty(notes = "文件资源服务器唯一路径")
    private String localPath;

    @JsonProperty("thumb_image_path")
    @ApiModelProperty(notes = "图片文件缩略图访问服务器路径")
    private String thumbImagePath;

    @JsonProperty("meta_data")
    @ApiModelProperty(notes = "文件元信息")
    private String metaData;

    @JsonProperty("deleted")
    @ApiModelProperty(notes = "文件是否已删除")
    private Boolean deleted;
}
