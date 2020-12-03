package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库修改用户密码请求对象")
public class DatabaseModifyPasswordRequestDTO {

    @NotBlank(message = "数据库用户id不能为空")
    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

    @NotBlank(message = "新密码不能为空")
    @JsonProperty("new_password")
    @ApiModelProperty(notes = "新密码")
    private String newPassword;

}
