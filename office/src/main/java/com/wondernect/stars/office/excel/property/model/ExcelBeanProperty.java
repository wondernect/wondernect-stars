package com.wondernect.stars.office.excel.property.model;

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
 * FileName: ExcelProperty
 * Author: chenxun
 * Date: 2020/11/16 14:13
 * Description: excel导入导出实体类属性
 */
@Entity
@Table(
        name = "excel_bean_property",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "beanId"),
                @Index(columnList = "name")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "excel导入导出实体类属性")
public class ExcelBeanProperty extends BaseStringModel {

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
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

    @JsonProperty("get_method_name")
    @ApiModelProperty(notes = "属性get方法名")
    private String getMethodName;

    @JsonProperty("set_method_name")
    @ApiModelProperty(notes = "属性set方法名")
    private String setMethodName;
}
