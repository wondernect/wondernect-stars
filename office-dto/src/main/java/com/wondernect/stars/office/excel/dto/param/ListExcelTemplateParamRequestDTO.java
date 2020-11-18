package com.wondernect.stars.office.excel.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板配置列表请求DTO
 *
 * @author chenxun 2020-11-17 16:20:18
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板配置列表请求对象")
public class ListExcelTemplateParamRequestDTO extends ListRequestDTO {

    @JsonProperty("template_id")
    @ApiModelProperty(notes = "模板id")
    private String templateId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "属性名")
    private String name;
}