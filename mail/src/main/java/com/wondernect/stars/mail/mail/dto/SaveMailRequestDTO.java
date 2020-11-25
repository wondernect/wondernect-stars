package com.wondernect.stars.mail.mail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.Map;

/**
 * 邮件请求DTO
 *
 * @author 王威 2020-11-23 11:21:47
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮件请求对象")
public class SaveMailRequestDTO {

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
    @ApiModelProperty("邮件服务器id")
    private String mailServerId;

    @JsonProperty("mail_template_id")
    @ApiModelProperty("邮件模板id")
    private String mailTemplateId;
}