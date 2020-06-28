package com.wondernect.stars.rbac.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 用户角色分页请求DTO
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户角色分页请求对象")
public class PageUserRoleRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @NotNull(message = "分页请求参数不能为空")
    @JsonProperty("page_request_data")
    @ApiModelProperty(notes = "分页请求参数")
    private PageRequestData pageRequestData;
}