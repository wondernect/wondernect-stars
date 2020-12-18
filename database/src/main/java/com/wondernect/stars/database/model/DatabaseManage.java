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
        name = "database_manage",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "databaseRootManageId"),
                @Index(columnList = "databaseName")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "数据库名称管理")
public class DatabaseManage extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 1980133829123975131L;

    @JsonProperty("database_root_manage_id")
    @ApiModelProperty(notes = "数据库id")
    private String databaseRootManageId;

    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;

    @JsonProperty("url")
    @ApiModelProperty(notes = "连接地址")
    private String url;

    @JsonProperty("init_state")
    @ApiModelProperty(notes = "初始化状态")
    private Boolean initState;

    @JsonProperty("init_message")
    @ApiModelProperty(notes = "初始化结果描述")
    private String initMessage;

    @JsonProperty("type")
    @ApiModelProperty(notes = "数据库类型")
    private String type;
}
