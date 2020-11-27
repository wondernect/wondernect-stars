package com.wondernect.stars.mail.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import com.wondernect.stars.mail.em.MailType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮箱服务器列表请求DTO
 *
 * @author 王威 2020-11-23 15:55:47
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "邮箱服务器列表请求对象")
public class ListMailServerRequestDTO extends ListRequestDTO {

    @JsonProperty("type")
    @ApiModelProperty(notes = "邮箱类型", allowableValues = "MAIL_126, MAIL_163, MAIL_QQ")
    private MailType type;
}