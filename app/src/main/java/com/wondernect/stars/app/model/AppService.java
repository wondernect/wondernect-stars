package com.wondernect.stars.app.model;

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

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppFile
 * Author: chenxun
 * Date: 2020-10-03 20:37
 * Description: 应用文件服务
 */
@Entity
@Table(name = "app_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "应用服务")
public class AppService extends BaseStringModel {

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用id")
    private String appId;

    @JsonProperty("root_file_path_id")
    @ApiModelProperty(notes = "应用本地存储文件根路径id")
    private String rootFilePathId;

    @JsonProperty("root_menu_id")
    @ApiModelProperty(notes = "应用根菜单id")
    private String rootMenuId;
}
