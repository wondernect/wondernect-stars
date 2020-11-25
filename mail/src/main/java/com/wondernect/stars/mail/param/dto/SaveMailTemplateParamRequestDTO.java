package com.wondernect.stars.mail.param.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件模板参数请求DTO
 *
 * @author 王威 2020-11-23 15:55:10
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件模板参数请求对象")
public class SaveMailTemplateParamRequestDTO {

    @JsonProperty("template_id")
    @ApiModelProperty(notes = "邮件模板")
    private String templateId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板内容中变量名称")
    private String name;

    @JsonProperty("param")
    @ApiModelProperty(notes = "传入替换数据Map<String, Object> varibles中key值")
    private String param;

    @JsonProperty("description")
    @ApiModelProperty(notes = "传入替换数据Map<String, Object> varibles中key值说明")
    private String description;

}