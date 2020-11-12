package com.wondernect.stars.database.dto;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库列表请求DTO
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Data
@NoArgsConstructor
//@AllArgsConstructor
@ApiModel(value = "数据库列表请求对象")
public class ListDatabaseManageRequestDTO extends ListRequestDTO {

}