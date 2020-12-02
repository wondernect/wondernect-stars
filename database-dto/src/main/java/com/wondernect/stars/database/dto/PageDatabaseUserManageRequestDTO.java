package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库用户分页请求DTO
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
@Data
@NoArgsConstructor
//@AllArgsConstructor
@ApiModel(value = "数据库用户分页请求对象")
public class PageDatabaseUserManageRequestDTO extends PageRequestDTO {

    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "MySQL数据库id")
    private String databaseRootManageId;
}