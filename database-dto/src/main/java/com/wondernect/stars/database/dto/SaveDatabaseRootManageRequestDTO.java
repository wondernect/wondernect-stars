package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 根数据库请求DTO
 *
 * @author 李亚飞 2020-12-01 15:00:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "根数据库请求对象")
public class SaveDatabaseRootManageRequestDTO {

    @NotNull
    @JsonProperty("server_ip")
    @ApiModelProperty(notes = "根数据库服务器ip地址")
    private String serverIp;

    @NotBlank
    @JsonProperty("port")
    @ApiModelProperty(notes = "根数据库连接端口")
    private Integer port;

    @NotNull
    @JsonProperty("username")
    @ApiModelProperty(notes = "根数据库用户名")
    private String username;

    @NotNull
    @JsonProperty("password")
    @ApiModelProperty(notes = "根数据库密码")
    private String password;
}