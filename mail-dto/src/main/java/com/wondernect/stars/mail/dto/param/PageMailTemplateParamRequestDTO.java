package com.wondernect.stars.mail.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮件模板参数分页请求DTO
 *
 * @author 王威 2020-11-23 15:55:10
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "邮件模板参数分页请求对象")
public class PageMailTemplateParamRequestDTO extends PageRequestDTO {

    @JsonProperty("template_id")
    @ApiModelProperty(notes = "邮件模板")
    private String templateId;
}