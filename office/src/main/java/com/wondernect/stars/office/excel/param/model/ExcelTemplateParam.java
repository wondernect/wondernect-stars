package com.wondernect.stars.office.excel.param.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
                @Index(columnList = "templateId"),
                @Index(columnList = "beanId"),
                @Index(columnList = "name")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "excel导入导出模板配置")
public class ExcelTemplateParam extends BaseStringModel {

    @JsonProperty("template_id")
    @ApiModelProperty("模板id")
    private String templateId;

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty("属性名")
    private String name;

    @JsonProperty("order_num")
    @ApiModelProperty(notes = "排序")
    private int orderNum = 0;
}
