package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.SortData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 应用列表请求DTO
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用列表请求对象")
public class ListAppRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id")
    private String userId;

    @JsonProperty("sort_data_list")
    @ApiModelProperty(notes = "列表请求参数")
    private List<SortData> sortDataList;
}