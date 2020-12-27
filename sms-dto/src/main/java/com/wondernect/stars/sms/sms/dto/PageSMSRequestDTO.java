package com.wondernect.stars.sms.sms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 短信分页请求DTO
 *
 * @author chenxun 2020-12-27 11:53:24
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "短信分页请求对象")
public class PageSMSRequestDTO extends PageRequestDTO {

    @JsonProperty("phone_number")
    @ApiModelProperty(notes = "手机号码")
    private String phoneNumber;

    @JsonProperty("send_result")
    @ApiModelProperty(notes = "发送结果")
    private Boolean sendResult;

    @JsonProperty("sms_template_id")
    @ApiModelProperty("短信模板id")
    private String smsTemplateId;
}