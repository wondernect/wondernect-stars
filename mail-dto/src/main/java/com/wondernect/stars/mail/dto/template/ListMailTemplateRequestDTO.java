package com.wondernect.stars.mail.dto.template;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件模板列表请求DTO
 *
 * @author 王威 2020-11-23 15:56:11
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮件模板列表请求对象")
public class ListMailTemplateRequestDTO extends ListRequestDTO {

}