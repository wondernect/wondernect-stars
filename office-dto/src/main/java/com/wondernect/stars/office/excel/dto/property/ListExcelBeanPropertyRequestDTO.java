package com.wondernect.stars.office.excel.dto.property;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * excel导入导出实体类属性列表请求DTO
 *
 * @author chenxun 2020-11-17 16:19:03
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "excel导入导出实体类属性列表请求对象")
public class ListExcelBeanPropertyRequestDTO extends ListRequestDTO {

}