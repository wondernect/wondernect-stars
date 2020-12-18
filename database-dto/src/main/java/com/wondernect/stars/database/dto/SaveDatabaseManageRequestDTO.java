package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 数据库请求DTO
 *
 * @author liyafei 2020-11-09 15:57:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库名称请求对象")
public class SaveDatabaseManageRequestDTO {

    @NotBlank(message = "数据库id不能为空")
    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;

    @NotBlank(message = "数据库名称不能为空")
    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;
}