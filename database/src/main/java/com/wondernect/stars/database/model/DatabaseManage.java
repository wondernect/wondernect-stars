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
                @Index(columnList = "createApp"),
                @Index(columnList = "databaseName")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "数据库")
public class DatabaseManage extends BaseStringModel implements Serializable {

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
}
