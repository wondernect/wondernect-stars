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
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SMSTemplateParam
 * Author: chenxun
 * Date: 2018/11/8 14:35
 * Description: 短信模板变量
 */
@Entity
@Table(name = "sms_template_param")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "短信模板变量")
public class SMSTemplateParam extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 2119957486276088688L;

    @JsonProperty("template_id")
    @ApiModelProperty("短信模板")
    private String templateId;

    @JsonProperty("name")
    @ApiModelProperty("模板内容中变量名称")
    private String name;

    @JsonProperty("param")
    @ApiModelProperty("传入替换数据Map<String, Object> varibles中key值")
    private String param;

    @JsonProperty("description")
    @ApiModelProperty("传入替换数据Map<String, Object> varibles中key值说明")
    private String description;
}
