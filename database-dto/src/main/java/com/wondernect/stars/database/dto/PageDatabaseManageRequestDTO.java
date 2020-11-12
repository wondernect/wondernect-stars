package com.wondernect.stars.database.dto;

import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库分页请求DTO
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Data
@NoArgsConstructor
//@AllArgsConstructor
@ApiModel(value = "数据库分页请求对象")
public class PageDatabaseManageRequestDTO extends PageRequestDTO {

}