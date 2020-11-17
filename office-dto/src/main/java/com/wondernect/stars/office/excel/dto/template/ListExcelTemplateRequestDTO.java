package com.wondernect.stars.office.excel.dto.template;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出模板列表请求DTO
 *
 * @author chenxun 2020-11-17 16:19:28
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "excel导入导出模板列表请求对象")
public class ListExcelTemplateRequestDTO extends ListRequestDTO {

}