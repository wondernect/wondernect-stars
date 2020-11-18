package com.wondernect.stars.office.excel.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出实体类属性列表请求DTO
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出实体类属性列表请求对象")
public class ListExcelBeanPropertyRequestDTO extends ListRequestDTO {

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;
}