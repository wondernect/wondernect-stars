package com.wondernect.stars.database.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 数据库请求DTO
 *
 * @author liyafei 2020-11-09 15:57:44
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "数据库请求对象")
public class SaveDatabaseManageRequestDTO {

    @NotNull
    @JsonProperty("database_name")
    @ApiModelProperty(notes = "数据库名称")
    private String databaseName;

    /*@JsonProperty("url")
    @ApiModelProperty(notes = "连接地址")
    private String url;

    @JsonProperty("init_state")
    @ApiModelProperty(notes = "初始化状态")
    private Boolean initState;

    @JsonProperty("init_message")
    @ApiModelProperty(notes = "初始化结果描述")
    private String initMessage;*/
}