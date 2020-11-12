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
@ApiModel(value = "数据库测试连接请求对象")
public class DatabaseConnectRequestDTO {

    @NotNull
    @JsonProperty("url")
    @ApiModelProperty(notes = "连接地址")
    private String url;

    @NotNull
    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @NotNull
    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

}
