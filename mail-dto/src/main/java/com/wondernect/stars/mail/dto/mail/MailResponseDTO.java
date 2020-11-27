package com.wondernect.stars.mail.dto.mail;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮件响应DTO
 *
 * @author 王威 2020-11-23 11:21:47
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件响应对象")
public class MailResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("from_address")
    @ApiModelProperty(notes = "寄件人地址")
    private String fromAddress;

    @JsonProperty("from_name")
    @ApiModelProperty(notes = "寄件人显示名称")
    private String fromName;

    @JsonProperty("to_address")
    @ApiModelProperty(notes = "收件人地址")
    private String toAddress;

    @JsonProperty("subject")
    @ApiModelProperty(notes = "主题")
    private String subject;

    @JsonProperty("content")
    @ApiModelProperty(notes = "内容")
    private String content;

    @JsonProperty("send_result")
    @ApiModelProperty(notes = "发送结果(成功|失败)")
    private Boolean sendResult;

    @JsonProperty("send_result_message")
    @ApiModelProperty(notes = "发送结果描述")
    private String sendResultMessage;

    @JsonProperty("mail_server_id")
    @ApiModelProperty(notes = "邮件服务器id")
    private String mailServerId;

    @JsonProperty("mail_template_id")
    @ApiModelProperty(notes = "邮件模板id")
    private String mailTemplateId;
}