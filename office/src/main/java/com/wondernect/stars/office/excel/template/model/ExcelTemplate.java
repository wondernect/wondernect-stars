package com.wondernect.stars.office.excel.template.model;

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
 * FileName: ExcelExportTemplate
 * Author: chenxun
 * Date: 2020/11/16 15:24
 * Description: excel导入导出模板
 */
@Entity
@Table(
        name = "excel_template",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "beanId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "excel导入导出模板")
public class ExcelTemplate extends BaseStringModel {

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;
}
