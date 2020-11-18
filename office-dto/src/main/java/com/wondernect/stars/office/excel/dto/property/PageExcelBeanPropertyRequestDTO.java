package com.wondernect.stars.office.excel.dto.property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * excel导入导出实体类属性分页请求DTO
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出实体类属性分页请求对象")
public class PageExcelBeanPropertyRequestDTO extends PageRequestDTO {

    @JsonProperty("bean_id")
    @ApiModelProperty(notes = "实体类id")
    private String beanId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;
}