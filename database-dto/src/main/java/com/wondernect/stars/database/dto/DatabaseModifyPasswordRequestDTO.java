package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库修改用户密码请求对象")
public class DatabaseModifyPasswordRequestDTO {

    @NotNull
    @JsonProperty("id")
    @ApiModelProperty(notes = "数据库用户id")
    private String id;

    @NotNull
    @JsonProperty("new_password")
    @ApiModelProperty(notes = "新密码")
    private String newPassword;

}
