package com.wondernect.stars.office.excel.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板分页请求DTO
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板分页请求对象")
public class PageExcelTemplateRequestDTO extends PageRequestDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;
}