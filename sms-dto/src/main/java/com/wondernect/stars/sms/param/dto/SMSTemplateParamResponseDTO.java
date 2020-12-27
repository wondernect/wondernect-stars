package com.wondernect.stars.sms.param.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信模板变量响应DTO
 *
 * @author chenxun 2020-12-27 11:54:43
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板变量响应对象")
public class SMSTemplateParamResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("template_id")
    @ApiModelProperty(notes = "短信模板")
    private String templateId;

    @JsonProperty("param")
    @ApiModelProperty(notes = "模板变量")
    private String param;

    @JsonProperty("description")
    @ApiModelProperty(notes = "模板变量说明")
    private String description;
}