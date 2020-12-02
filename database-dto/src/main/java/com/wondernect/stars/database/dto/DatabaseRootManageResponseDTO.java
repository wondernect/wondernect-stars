package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 根数据库响应DTO
 *
 * @author 李亚飞 2020-12-01 15:00:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "根数据库响应对象")
public class DatabaseRootManageResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("driver")
    @ApiModelProperty(notes = "数据库驱动")
    private String driver;

    @JsonProperty("server_ip")
    @ApiModelProperty(notes = "根数据库服务器ip地址")
    private String serverIp;

    @JsonProperty("port")
    @ApiModelProperty(notes = "根数据库连接端口")
    private Integer port;

    @JsonProperty("url")
    @ApiModelProperty(notes = "根数据库连接地址")
    private String url;

    @JsonProperty("username")
    @ApiModelProperty(notes = "根数据库用户名")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "根数据库密码")
    private String password;

    /*@JsonProperty("secret")
    @ApiModelProperty(notes = "标识码")
    private String secret;*/
}