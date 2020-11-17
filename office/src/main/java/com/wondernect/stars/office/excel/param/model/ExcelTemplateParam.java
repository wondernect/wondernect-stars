package com.wondernect.stars.office.excel.param.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ExcelTemplateParams
 * Author: chenxun
 * Date: 2020/11/16 15:29
 * Description: excel导入导出模板配置
 */
@Entity
@Table(
        name = "excel_template_param",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "templateId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "excel导入导出模板配置")
public class ExcelTemplateParam extends BaseStringModel {

    @JsonProperty("template_id")
    @ApiModelProperty("模板id")
    private String templateId;

    @JsonProperty("name")
    @ApiModelProperty("属性名")
    private String name;

    @JsonProperty("type")
    @ApiModelProperty(notes = "属性类型")
    private String type;

    @JsonProperty("title")
    @ApiModelProperty(notes = "属性导入导出标题")
    private String title;

    @JsonProperty("order_num")
    @ApiModelProperty(notes = "排序")
    private int orderNum = 0;
}
