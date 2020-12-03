package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库连接测试响应对象")
public class TestConnectResponseDTO {

    @JsonProperty("url")
    @ApiModelProperty(notes = "连接地址")
    private String url;

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户名称")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

    @JsonProperty("connect_state")
    @ApiModelProperty(notes = "测试连接结果")
    private Boolean connectState;

    @JsonProperty("connect_message")
    @ApiModelProperty(notes = "测试连接结果描述")
    private String connectMessage;
}
