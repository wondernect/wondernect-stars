package com.wondernect.stars.office.excel.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * excel导入导出模板配置请求DTO
 *
 * @author chenxun 2020-11-17 16:20:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板配置请求对象")
public class SaveExcelTemplateParamRequestDTO {

    @NotBlank(message = "模板id不能为空")
    @JsonProperty("template_id")
    @ApiModelProperty(notes = "模板id")
    private String templateId;

    @NotBlank(message = "属性名不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;

    @JsonProperty("type")
    @ApiModelProperty(notes = "属性类型")
    private String type;

    @NotBlank(message = "标题不能为空")
    @JsonProperty("title")
    @ApiModelProperty(notes = "属性导入导出标题")
    private String title;

    @JsonProperty("order_num")
    @ApiModelProperty(notes = "排序")
    private int orderNum = 0;
}