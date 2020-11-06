package com.wondernect.stars.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = -1086809486497743607L;

    @Lob
    @JsonProperty("message")
    @ApiModelProperty("短信格式化后内容")
    private String message;

    @JsonProperty("phone_number")
    @ApiModelProperty("短信接收者号码(默认只接收一个手机号码,不支持批量支持)")
    private String phoneNumber;

    @JsonProperty("send_result")
    @ApiModelProperty(notes = "发送结果(成功|失败)")
    private Boolean sendResult;

    @JsonProperty("send_result_code")
    @ApiModelProperty(notes = "发送结果状态码")
    private String sendResultCode;

    @JsonProperty("send_result_message")
    @ApiModelProperty(notes = "发送结果状态码描述")
    private String sendResultMessage;
}
