package com.wondernect.stars.office.excel.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * excel导入导出实体类属性响应DTO
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出实体类属性响应对象")
public class ExcelBeanPropertyResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;

    @JsonProperty("type")
    @ApiModelProperty(notes = "属性类型")
    private String type;

    @JsonProperty("title")
    @ApiModelProperty(notes = "属性导入导出标题")
    private String title;

    @JsonProperty("order")
    @ApiModelProperty(notes = "排序")
    private int order;

    @JsonProperty("get_method_name")
    @ApiModelProperty(notes = "属性get方法名")
    private String getMethodName;

    @JsonProperty("set_method_name")
    @ApiModelProperty(notes = "属性set方法名")
    private String setMethodName;
}