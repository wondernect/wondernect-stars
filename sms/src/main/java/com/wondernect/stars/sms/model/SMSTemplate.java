package com.wondernect.stars.sms.model;

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
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SMSTemplate
 * Author: chenxun
 * Date: 2018/11/8 14:23
 * Description: 短信模板
 */
@Entity
@Table(name = "sms_template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "短信模板")
public class SMSTemplate extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 8612562965955497011L;

    @JsonProperty("name")
    @ApiModelProperty("模板名称")
    private String name;

    @JsonProperty("content")
    @ApiModelProperty("模板内容")
    private String content;

    @JsonProperty("description")
    @ApiModelProperty("模板填写说明")
    private String description;
}
