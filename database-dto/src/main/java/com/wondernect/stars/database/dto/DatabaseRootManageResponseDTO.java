package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import com.wondernect.stars.database.em.DatabaseType;
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
@ApiModel(value = "数据库响应对象")
public class DatabaseRootManageResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("type")
    @ApiModelProperty(notes = "数据库类型")
    private DatabaseType type;

    @JsonProperty("driver")
    @ApiModelProperty(notes = "数据库驱动")
    private String driver;

    @JsonProperty("ip")
    @ApiModelProperty(notes = "数据库ip地址")
    private String ip;

    @JsonProperty("port")
    @ApiModelProperty(notes = "数据库连接端口")
    private Integer port;

    @JsonProperty("url")
    @ApiModelProperty(notes = "数据库连接地址")
    private String url;

    @JsonProperty("username")
    @ApiModelProperty(notes = "数据库用户名")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "数据库密码")
    private String password;

}