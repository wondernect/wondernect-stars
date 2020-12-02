package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 数据库用户请求DTO
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户创建请求对象")
public class SaveDatabaseUserManageRequestDTO {

    @NotNull
    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "MySQL数据库id")
    private String databaseRootManageId;

    @NotNull
    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @NotNull
    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

}