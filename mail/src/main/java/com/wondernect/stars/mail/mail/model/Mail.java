package com.wondernect.stars.mail.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2017/10/26.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "mail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮件")
public class Mail extends BaseStringModel implements Serializable {

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

    @Lob
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
