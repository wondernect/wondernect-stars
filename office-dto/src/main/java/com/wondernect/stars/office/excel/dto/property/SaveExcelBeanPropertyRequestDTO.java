package com.wondernect.stars.office.excel.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * excel导入导出实体类属性请求DTO
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出实体类属性请求对象")
public class SaveExcelBeanPropertyRequestDTO {

    @NotBlank(message = "实体类id不能为空")
    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @NotBlank(message = "属性名不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;

    @NotBlank(message = "属性类型不能为空")
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

    @JsonProperty("get_method_name")
    @ApiModelProperty(notes = "属性get方法名")
    private String getMethodName;

    @JsonProperty("set_method_name")
    @ApiModelProperty(notes = "属性set方法名")
    private String setMethodName;
}