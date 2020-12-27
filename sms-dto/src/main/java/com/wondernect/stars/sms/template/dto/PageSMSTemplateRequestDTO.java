package com.wondernect.stars.sms.template.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信模板分页请求DTO
 *
 * @author chenxun 2020-12-27 11:54:09
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信模板分页请求对象")
public class PageSMSTemplateRequestDTO extends PageRequestDTO {

    @JsonProperty("name")
    @ApiModelProperty("模板名称(模糊搜索)")
    private String name;
}