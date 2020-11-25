package com.wondernect.stars.mail.server.dto;

import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮箱服务器列表请求DTO
 *
 * @author 王威 2020-11-23 15:55:47
 **/
@Data
@NoArgsConstructor
@ApiModel(value = "邮箱服务器列表请求对象")
public class ListMailServerRequestDTO extends ListRequestDTO {

}