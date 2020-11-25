package com.wondernect.stars.mail.mail.dto;

import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮件分页请求DTO
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮件分页请求对象")
public class PageMailRequestDTO extends PageRequestDTO {

}