package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import com.wondernect.elements.rdb.request.PageRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: PageFileRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文件分页请求对象")
public class PageFileRequestDTO extends PageRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "上传用户id")
    private String userId;

    @JsonProperty("deleted")
    @ApiModelProperty(notes = "文件是否已删除")
    private Boolean deleted;
}
