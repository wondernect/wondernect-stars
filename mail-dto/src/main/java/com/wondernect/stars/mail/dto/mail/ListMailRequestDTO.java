package com.wondernect.stars.mail.dto.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮件列表请求DTO
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "邮件列表请求对象")
public class ListMailRequestDTO extends ListRequestDTO {

    @JsonProperty("mail_server_id")
    @ApiModelProperty("邮件服务器id")
    private String mailServerId;

    @JsonProperty("mail_template_id")
    @ApiModelProperty("邮件模板id")
    private String mailTemplateId;
}