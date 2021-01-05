package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 应用分页请求DTO
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用分页请求对象")
public class PageAppRequestDTO extends PageRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id", hidden = true)
    private String userId;
}