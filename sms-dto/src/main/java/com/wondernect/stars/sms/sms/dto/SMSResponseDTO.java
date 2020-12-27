package com.wondernect.stars.sms.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信响应DTO
 *
 * @author chenxun 2020-12-27 11:53:24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信响应对象")
public class SMSResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("phone_number")
    @ApiModelProperty(notes = "手机号码")
    private String phoneNumber;

    @JsonProperty("content")
    @ApiModelProperty(notes = "短信内容")
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