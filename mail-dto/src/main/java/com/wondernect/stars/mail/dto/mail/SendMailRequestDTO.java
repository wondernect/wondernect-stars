package com.wondernect.stars.mail.dto.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * 邮件请求DTO
 *
 * @author 王威 2020-11-23 11:21:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件发送请求对象")
public class SendMailRequestDTO {

    @NotBlank(message = "邮件服务器id不能为空")
    @JsonProperty("mail_server_id")
    @ApiModelProperty("邮件服务器id")
    private String mailServerId;

    @NotBlank(message = "邮件模板id不能为空")
    @JsonProperty("mail_template_id")
    @ApiModelProperty("邮件模板id")
    private String mailTemplateId;

    @NotBlank(message = "收件人地址不能为空")
    @JsonProperty("to_address")
    @ApiModelProperty(notes = "收件人地址")
    private String toAddress;

    @JsonProperty("attachment")
    @ApiModelProperty(notes = "动态模板数据")
    private Map<String, Object> attachment;
}