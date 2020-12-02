package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库用户权限关系表列表请求DTO
 *
 * @author 李亚飞 2020-12-02 14:25:41
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "数据库用户权限关系表列表请求对象")
public class ListDatabaseUserRightsShipRequestDTO extends ListRequestDTO {

    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

}