package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库分页请求DTO
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库分页请求对象")
public class PageDatabaseManageRequestDTO extends PageRequestDTO {

    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;

    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;
}