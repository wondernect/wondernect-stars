package com.wondernect.stars.sms.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SMS
 * Author: chenxun
 * Date: 2018/11/8 14:02
 * Description: 短信 - Short Message Service
 */
@Entity
@Table(name = "sms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "短信")
public class SMS extends BaseStringModel implements Serializable {

    @JsonProperty("phone_number")
    @ApiModelProperty("手机号码")
    private String phoneNumber;

    @Lob
    @JsonProperty("content")
    @ApiModelProperty("短信内容")
    private String content;

    @JsonProperty("send_result")
    @ApiModelProperty(notes = "发送结果")
    private Boolean sendResult;

    @JsonProperty("send_result_code")
    @ApiModelProperty(notes = "发送结果状态码")
    private String sendResultCode;

    @JsonProperty("send_result_message")
    @ApiModelProperty(notes = "发送结果状态码描述")
    private String sendResultMessage;

    @JsonProperty("sms_template_id")
    @ApiModelProperty("短信模板id")
    private String smsTemplateId;
}
