package com.wondernect.stars.office.excel.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板列表请求DTO
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板列表请求对象")
public class ListExcelTemplateRequestDTO extends ListRequestDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "模板名称")
    private String name;
}