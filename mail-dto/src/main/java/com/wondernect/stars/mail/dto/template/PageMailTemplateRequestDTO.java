package com.wondernect.stars.mail.dto.template;

import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件模板分页请求DTO
 *
 * @author 王威 2020-11-23 15:56:11
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮件模板分页请求对象")
public class PageMailTemplateRequestDTO extends PageRequestDTO {

}