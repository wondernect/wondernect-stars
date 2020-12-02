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
    @ApiModelProperty(notes = "MySQL数据库id")
    private String databaseRootManageId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

    /*@JsonProperty("rights_state")
    @ApiModelProperty(notes = "权限状态(0-无权限，1-只读权限，2-所有权限)", allowableValues = "0, 1, 2")
    private int rightsState;

    @JsonProperty("rights_message")
    @ApiModelProperty(notes = "权限结果描述")
    private String rightsMessage;*/
}