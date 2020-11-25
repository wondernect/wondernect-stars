package com.wondernect.stars.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.mail.em.MailTemplateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: MailTemplate
 * Author: chenxun
 * Date: 2018/11/9 17:14
 * Description: 邮件模板
 */
@Entity
@Table(name = "mail_template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮件模板")
public class MailTemplate extends BaseStringModel implements Serializable {

    @Enumerated(EnumType.STRING)
    @JsonProperty("type")
    @ApiModelProperty("模板类型")
    private MailTemplateType type;

    @JsonProperty("name")
    @ApiModelProperty("模板名称")
    private String name;

    @JsonProperty("personal")
    @ApiModelProperty("邮件用户名定制")
    private String personal;

    @JsonProperty("subject")
    @ApiModelProperty("主题")
    private String subject;

    @Lob
    @JsonProperty("name")
    @ApiModelProperty("模板内容")
    private String content;

    @JsonProperty("url")
    @ApiModelProperty("模板链接地址")
    private String url;

    @JsonProperty("description")
    @ApiModelProperty("模板说明")
    private String description;
}
