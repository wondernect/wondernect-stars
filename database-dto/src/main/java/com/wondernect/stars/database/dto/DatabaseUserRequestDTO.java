package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户请求对象")
public class DatabaseUserRequestDTO {

    @NotNull
    @JsonProperty("database_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseManageId;

    @NotNull
    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;

    @NotNull
    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @NotNull
    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;
}
