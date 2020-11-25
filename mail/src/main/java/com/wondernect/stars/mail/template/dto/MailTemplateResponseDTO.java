package com.wondernect.stars.mail.template.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import com.wondernect.stars.mail.em.MailTemplateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件模板响应DTO
 *
 * @author 王威 2020-11-23 15:56:11
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件模板响应对象")
public class MailTemplateResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("type")
    @ApiModelProperty(notes = "模板类型")
    private MailTemplateType type;

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;

    @JsonProperty("personal")
    @ApiModelProperty(notes = "邮件用户名定制")
    private String personal;

    @JsonProperty("subject")
    @ApiModelProperty(notes = "主题")
    private String subject;

    @JsonProperty("content")
    @ApiModelProperty(notes = "模板内容")
    private String content;

    @JsonProperty("url")
    @ApiModelProperty(notes = "模板链接地址")
    private String url;

    @JsonProperty("description")
    @ApiModelProperty(notes = "模板说明")
    private String description;
}