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
        name = "database_user_rights_ship",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "databaseManageId"),
                @Index(columnList = "databaseUserId")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "数据库用户权限关系表")
public class DatabaseUserRightsShip extends BaseStringModel implements Serializable {

    @JsonProperty("database_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseManageId;

    @JsonProperty("database_user_id")
    @ApiModelProperty(notes = "数据库用户id")
    private String databaseUserId;

    @JsonProperty("rights_state")
    @ApiModelProperty(notes = "权限状态(1-只读权限，2-所有权限)", allowableValues = "1, 2")
    private int rightsState;

    @JsonProperty("rights_message")
    @ApiModelProperty(notes = "权限结果描述")
    private String rightsMessage;
}
