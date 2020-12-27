package com.wondernect.stars.sms.template.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 短信模板请求DTO
 *
 * @author chenxun 2020-12-27 11:54:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板请求对象")
public class SaveSMSTemplateRequestDTO {

    @Length(max = 100, message = "模板名称长度不能超过100")
    @NotBlank(message = "模板名称不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;

    @Length(max = 100, message = "模板内容长度不能超过100")
    @NotBlank(message = "模板内容不能为空")
    @JsonProperty("content")
    @ApiModelProperty(notes = "模板内容")
    private String content;

    @JsonProperty("description")
    @ApiModelProperty(notes = "模板填写说明")
    private String description;
}