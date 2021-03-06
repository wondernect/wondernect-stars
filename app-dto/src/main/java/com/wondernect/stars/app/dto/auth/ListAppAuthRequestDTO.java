package com.wondernect.stars.app.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 应用访问认证列表请求DTO
 *
 * @author chenxun 2020-12-29 16:28:22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用访问认证列表请求对象")
public class ListAppAuthRequestDTO extends ListRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "应用访问密钥绑定用户id", hidden = true)
    private String userId;

    @JsonProperty("create_user")
    @ApiModelProperty(notes = "应用访问密钥创建用户id", hidden = true)
    private String createUser;
}