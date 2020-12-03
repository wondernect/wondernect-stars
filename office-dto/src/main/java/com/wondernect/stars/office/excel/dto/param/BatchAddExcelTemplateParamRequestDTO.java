package com.wondernect.stars.office.excel.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * excel导入导出模板配置请求DTO
 *
 * @author chenxun 2020-11-17 16:20:18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "excel导入导出模板配置批量添加请求对象")
public class BatchAddExcelTemplateParamRequestDTO {

    @NotBlank(message = "模板id不能为空")
    @JsonProperty("template_id")
    @ApiModelProperty(notes = "模板id")
    private String templateId;

    @NotNull(message = "添加实体类属性id列表不能为空")
    @JsonProperty("bean_property_id_list")
    @ApiModelProperty(notes = "实体类属性id列表")
    private List<String> beanPropertyIdList;
}