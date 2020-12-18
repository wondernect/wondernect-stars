package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 数据库列表请求DTO
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库名称列表请求对象")
public class ListDatabaseManageRequestDTO extends ListRequestDTO {

    @NotBlank(message = "数据库id不能为空")
    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;

    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;
}