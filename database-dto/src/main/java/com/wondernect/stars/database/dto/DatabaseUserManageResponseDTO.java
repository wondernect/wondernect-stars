package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库用户响应DTO
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户响应对象")
public class DatabaseUserManageResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户名称")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

}