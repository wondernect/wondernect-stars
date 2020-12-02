package com.wondernect.stars.database.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(
        name = "database_user_manage",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "databaseManageId"),
                @Index(columnList = "userName")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "数据库用户管理")
public class DatabaseUserManage extends BaseStringModel implements Serializable {

    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "MySQL数据库id")
    private String databaseRootManageId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "用户名称")
    private String userName;

    @JsonProperty("password")
    @ApiModelProperty(notes = "用户密码")
    private String password;

   /* @JsonProperty("rights_state")
    @ApiModelProperty(notes = "权限状态(0-无权限，1-只读权限，2-所有权限)", allowableValues = "0, 1, 2")
    private int rightsState;

    @JsonProperty("rights_message")
    @ApiModelProperty(notes = "权限结果描述")
    private String rightsMessage;*/
}
