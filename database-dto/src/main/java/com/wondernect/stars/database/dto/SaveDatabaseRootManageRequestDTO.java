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
@ApiModel(value = "数据库请求对象")
public class SaveDatabaseRootManageRequestDTO {

    /*@NotBlank(message = "数据库驱动不能为空")
    @JsonProperty("driver")
    @ApiModelProperty(notes = "数据库驱动")
    private String driver;*/

    @NotBlank(message = "数据库ip地址不能为空")
    @JsonProperty("ip")
    @ApiModelProperty(notes = "数据库ip地址")
    private String ip;

    @NotNull(message = "数据库连接端口不能为空")
    @JsonProperty("port")
    @ApiModelProperty(notes = "数据库连接端口")
    private Integer port;

    @NotBlank(message = "数据库用户名不能为空")
    @JsonProperty("username")
    @ApiModelProperty(notes = "数据库用户名")
    private String username;

    @NotBlank(message = "数据库密码不能为空")
    @JsonProperty("password")
    @ApiModelProperty(notes = "数据库密码")
    private String password;
}