package com.wondernect.stars.sms.param.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 短信模板变量请求DTO
 *
 * @author chenxun 2020-12-27 11:54:43
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板变量请求对象")
public class SaveSMSTemplateParamRequestDTO {

    @NotBlank(message = "短信模板不能为空")
    @JsonProperty("template_id")
    @ApiModelProperty(notes = "短信模板")
    private String templateId;

    @Length(max = 32, message = "模板变量长度不能超过32")
    @NotBlank(message = "模板变量不能为空")
    @JsonProperty("param")
    @ApiModelProperty(notes = "模板变量")
    private String param;

    @NotBlank(message = "模板变量说明不能为空")
    @JsonProperty("description")
    @ApiModelProperty(notes = "模板变量说明")
    private String description;
}