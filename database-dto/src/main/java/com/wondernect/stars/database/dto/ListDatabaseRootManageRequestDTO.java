package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * 根数据库列表请求DTO
 *
 * @author 李亚飞 2020-12-01 15:00:01
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "根数据库列表请求对象")
public class ListDatabaseRootManageRequestDTO extends ListRequestDTO {

    @JsonProperty("server_ip")
    @ApiModelProperty(notes = "根数据库服务器ip地址")
    private String serverIp;

}