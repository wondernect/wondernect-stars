package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "数据库名称id不能为空")
    @JsonProperty("database_manage_id")
    @ApiModelProperty(notes = "数据库名称id")
    private String databaseManageId;

    @NotBlank(message = "数据库用户id不能为空")
    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

}