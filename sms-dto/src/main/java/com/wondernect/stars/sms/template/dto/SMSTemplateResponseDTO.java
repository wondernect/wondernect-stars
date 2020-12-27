package com.wondernect.stars.sms.template.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信模板响应DTO
 *
 * @author chenxun 2020-12-27 11:54:09
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板响应对象")
public class SMSTemplateResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;

    @JsonProperty("content")
    @ApiModelProperty(notes = "模板内容")
    private String content;

    @JsonProperty("description")
    @ApiModelProperty(notes = "模板填写说明")
    private String description;
}