package com.wondernect.stars.user.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户角色响应DTO
 *
 * @author chenxun 2020-06-28 21:46:01
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户角色响应对象")
public class UserRoleResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;
}