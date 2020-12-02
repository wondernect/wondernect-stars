package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库用户权限关系表响应DTO
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户权限关系表响应对象")
public class DatabaseUserRightsShipResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("database_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseManageId;

    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;

    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @JsonProperty("rights_state")
    @ApiModelProperty(notes = "权限状态(0-无权限，1-只读权限，2-所有权限)")
    private int rightsState;

    @JsonProperty("rights_message")
    @ApiModelProperty(notes = "权限结果描述")
    private String rightsMessage;
}