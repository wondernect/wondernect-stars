package com.wondernect.stars.sms.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 短信请求DTO
 *
 * @author chenxun 2020-12-27 11:53:24
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信请求对象")
public class SaveSMSRequestDTO {

    @Length(max = 32, message = "手机号码长度不能超过32")
    @NotBlank(message = "手机号码不能为空")
    @JsonProperty("phone_number")
    @ApiModelProperty(notes = "手机号码")
    private String phoneNumber;

    @Length(max = 200, message = "短信内容长度不能超过200")
    @NotBlank(message = "短信内容不能为空")
    @JsonProperty("content")
    @ApiModelProperty(notes = "短信内容")
    private String content;

    @NotNull(message = "发送结果不能为空")
    @JsonProperty("send_result")
    @ApiModelProperty(notes = "发送结果")
    private Boolean sendResult;

    @JsonProperty("send_result_code")
    @ApiModelProperty(notes = "发送结果状态码")
    private String sendResultCode;

    @JsonProperty("send_result_message")
    @ApiModelProperty(notes = "发送结果状态码描述")
    private String sendResultMessage;

    @NotBlank(message = "短信模板id不能为空")
    @JsonProperty("sms_template_id")
    @ApiModelProperty("短信模板id")
    private String smsTemplateId;
}