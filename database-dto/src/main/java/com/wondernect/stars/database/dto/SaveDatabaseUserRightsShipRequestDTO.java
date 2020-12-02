package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 数据库用户权限关系表请求DTO
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户权限关系表请求对象")
public class SaveDatabaseUserRightsShipRequestDTO {

    @NotNull
    @JsonProperty("database_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseManageId;

    @NotNull
    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

   /* @JsonProperty("rights_state")
    @ApiModelProperty(notes = "权限状态(0-无权限，1-只读权限，2-所有权限)")
    private int rightsState;

    @JsonProperty("rights_message")
    @ApiModelProperty(notes = "权限结果描述")
    private String rightsMessage;*/
}