package com.wondernect.stars.mail.param.model;

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
 * FileName: MailTemplateParam
 * Author: chenxun
 * Date: 2018/11/12 10:14
 * Description: 邮件模板参数
 */
@Entity
@Table(name = "mail_template_param")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮件模板参数")
public class MailTemplateParam extends BaseStringModel implements Serializable {

    @JsonProperty("template_id")
    @ApiModelProperty("邮件模板")
    private String templateId;

    @JsonProperty("name")
    @ApiModelProperty("模板内容中变量名称")
    private String name;

    @JsonProperty("param")
    @ApiModelProperty("传入替换数据Map<String, Object> varibles中key值")
    private String param;

    @JsonProperty("description")
    @ApiModelProperty("传入替换数据Map<String, Object> varibles中key值说明")
    private String description;
}
