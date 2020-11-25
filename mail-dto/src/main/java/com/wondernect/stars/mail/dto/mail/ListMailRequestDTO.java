package com.wondernect.stars.mail.dto.mail;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件列表请求DTO
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮件列表请求对象")
public class ListMailRequestDTO extends ListRequestDTO {

}