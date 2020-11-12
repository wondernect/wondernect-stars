package com.wondernect.stars.database.dto;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库用户列表请求DTO
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
@Data
@NoArgsConstructor
//@AllArgsConstructor
@ApiModel(value = "数据库用户列表请求对象")
public class ListDatabaseUserManageRequestDTO extends ListRequestDTO {

}