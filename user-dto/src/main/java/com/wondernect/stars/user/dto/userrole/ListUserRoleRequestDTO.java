package com.wondernect.stars.user.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.SortData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户角色列表请求DTO
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户角色列表请求对象")
public class ListUserRoleRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @JsonProperty("sort_data_list")
    @ApiModelProperty(notes = "列表请求参数")
    private List<SortData> sortDataList;
}