package com.wondernect.stars.office.excel.dto.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * excel导入导出实体类分页请求DTO
 *
 * @author chenxun 2020-11-17 16:18:30
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出实体类分页请求对象")
public class PageExcelBeanRequestDTO extends PageRequestDTO {

    @JsonProperty("bean")
    @ApiModelProperty(notes = "实体类")
    private String bean;

    @JsonProperty("name")
    @ApiModelProperty(notes = "实体类名称")
    private String name;
}