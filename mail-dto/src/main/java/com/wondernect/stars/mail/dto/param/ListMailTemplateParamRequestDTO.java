package com.wondernect.stars.mail.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件模板参数列表请求DTO
 *
 * @author 王威 2020-11-23 15:55:10
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮件模板参数列表请求对象")
public class ListMailTemplateParamRequestDTO extends ListRequestDTO {

    @JsonProperty("search_text")
    @ApiModelProperty(notes = "搜索参数")
    private String searchText;
}