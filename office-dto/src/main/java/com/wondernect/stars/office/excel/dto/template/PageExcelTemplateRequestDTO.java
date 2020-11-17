package com.wondernect.stars.office.excel.dto.template;

import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板分页请求DTO
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "excel导入导出模板分页请求对象")
public class PageExcelTemplateRequestDTO extends PageRequestDTO {

}