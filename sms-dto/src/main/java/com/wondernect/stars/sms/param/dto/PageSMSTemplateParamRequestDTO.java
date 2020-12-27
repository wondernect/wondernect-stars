package com.wondernect.stars.sms.param.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信模板变量分页请求DTO
 *
 * @author chenxun 2020-12-27 11:54:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板变量分页请求对象")
public class PageSMSTemplateParamRequestDTO extends PageRequestDTO {

    @JsonProperty("template_id")
    @ApiModelProperty("短信模板id")
    private String templateId;

    @JsonProperty("param")
    @ApiModelProperty("模板变量")
    private String param;
}