package com.wondernect.stars.user.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户角色分页请求DTO
 *
 * @author chenxun 2020-06-28 21:46:02
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户角色分页请求对象")
public class PageUserRoleRequestDTO extends PageRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;
}