package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 应用分页请求DTO
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用分页请求对象")
public class PageAppRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id")
    private String userId;

    @NotNull(message = "分页请求参数不能为空")
    @JsonProperty("page_request_data")
    @ApiModelProperty(notes = "分页请求参数")
    private PageRequestData pageRequestData;
}