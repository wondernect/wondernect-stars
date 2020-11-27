package com.wondernect.stars.mail.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮件模板参数响应DTO
 *
 * @author 王威 2020-11-23 15:55:10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件模板参数响应对象")
public class MailTemplateParamResponseDTO extends BaseStringResponseDTO {

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