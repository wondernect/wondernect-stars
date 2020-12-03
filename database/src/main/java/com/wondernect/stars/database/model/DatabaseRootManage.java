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
        name = "database_root_manage",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "serverIp")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "MySQL数据库管理")
public class DatabaseRootManage extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 7827047990969051918L;

    @JsonProperty("driver")
    @ApiModelProperty(notes = "MySQL数据库驱动")
    private String driver;

    @JsonProperty("server_ip")
    @ApiModelProperty(notes = "MySQL数据库服务器ip地址")
    private String serverIp;

    @JsonProperty("port")
    @ApiModelProperty(notes = "MySQL数据库连接端口")
    private Integer port;

    @JsonProperty("url")
    @ApiModelProperty(notes = "MySQL数据库连接地址")
    private String url;

    @JsonProperty("username")
    @ApiModelProperty(notes = "MySQL数据库用户名")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "MySQL数据库密码")
    private String password;
}
