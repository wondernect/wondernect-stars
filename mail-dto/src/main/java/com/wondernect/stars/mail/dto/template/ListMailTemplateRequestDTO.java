package com.wondernect.stars.mail.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import com.wondernect.stars.mail.em.MailTemplateType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮件模板列表请求DTO
 *
 * @author 王威 2020-11-23 15:56:11
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "邮件模板列表请求对象")
public class ListMailTemplateRequestDTO extends ListRequestDTO {

    @JsonProperty("type")
    @ApiModelProperty(value = "模板类型", allowableValues = "PLAIN_TEXT, HTML_STRING, HTML_TEMPLATE")
    private MailTemplateType type;
}