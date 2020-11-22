package com.wondernect.stars.office.excel.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * excel导入导出模板请求DTO
 *
 * @author chenxun 2020-11-17 16:19:27
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板请求对象")
public class SaveExcelTemplateRequestDTO {

    @NotBlank(message = "实体类id不能为空")
    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @NotBlank(message = "模板名称不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;
}