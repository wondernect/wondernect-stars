package com.wondernect.stars.office.excel.bean.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: ExcelEntity
 * Author: chenxun
 * Date: 2020/11/16 14:13
 * Description: excel导入导出实体类
 */
@Entity
@Table(
        name = "excel_bean",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "entity")
        }
)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "excel导入导出实体类")
public class ExcelBean extends BaseStringModel {

    @JsonProperty("bean")
    @ApiModelProperty(notes = "实体类")
    private String bean;

    @JsonProperty("name")
    @ApiModelProperty(notes = "实体类名称")
    private String name;
}
