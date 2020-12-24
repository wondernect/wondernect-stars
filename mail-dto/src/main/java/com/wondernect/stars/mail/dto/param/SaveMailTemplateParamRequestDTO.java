package com.wondernect.stars.mail.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "邮件模板id不能为空")
    @JsonProperty("template_id")
    @ApiModelProperty(notes = "邮件模板")
    private String templateId;

    @NotBlank(message = "替换数据key值不能为空")
    @JsonProperty("param")
    @ApiModelProperty(notes = "传入替换数据Map<String, Object> varibles中key值")
    private String param;

    @NotBlank(message = "替换数据key值说明不能为空")
    @JsonProperty("description")
    @ApiModelProperty(notes = "传入替换数据Map<String, Object> varibles中key值说明")
    private String description;

}