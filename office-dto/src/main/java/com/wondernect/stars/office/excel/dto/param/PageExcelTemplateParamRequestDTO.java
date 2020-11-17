package com.wondernect.stars.office.excel.dto.param;

import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板配置分页请求DTO
 *
 * @author chenxun 2020-11-17 16:20:18
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "excel导入导出模板配置分页请求对象")
public class PageExcelTemplateParamRequestDTO extends PageRequestDTO {

}