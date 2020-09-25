package com.wondernect.stars.file.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ListFileRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "文件列表请求对象")
public class ListFileRequestDTO extends ListRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "上传用户id")
    private String userId;

    @JsonProperty("deleted")
    @ApiModelProperty(notes = "文件是否已删除")
    private Boolean deleted;
}
