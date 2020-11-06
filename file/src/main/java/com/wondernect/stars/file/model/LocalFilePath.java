package com.wondernect.stars.file.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: Path
 * Author: chenxun
 * Date: 2020-06-26 18:51
 * Description: 文件存储路径(针对本地存储)
 */
@Entity
@Table(name = "local_file_path")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "文件存储路径")
public class LocalFilePath extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 672505523629619138L;

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

    @JsonProperty("is_deleted")
    @ApiModelProperty(notes = "是否删除")
    private Boolean isDeleted = false;
}
