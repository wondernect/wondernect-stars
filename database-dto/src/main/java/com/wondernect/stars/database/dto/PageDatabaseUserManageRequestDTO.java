package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 数据库用户分页请求DTO
 *
 * @author liyafei 2020-11-09 15:58:15
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库用户分页请求对象")
public class PageDatabaseUserManageRequestDTO extends PageRequestDTO {

    @NotBlank(message = "数据库id不能为空")
    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;
}