package com.wondernect.stars.mail.feign.mail;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.mail.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/11/26 11:41
 * @Version 1.0
 */

@FeignClient(name = "${wondernect.stars.mail.feign.name}", url = "${wondernect.stars.mail.feign.url}", path = "/v1/wondernect/mail", configuration = WondernectFeignConfiguration.class)
public interface MailFeignClient {

    @ApiOperation(value = "发送邮件", httpMethod = "POST")
    @PostMapping(value = "/send")
    public BusinessData<MailResponseDTO> send(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SendMailRequestDTO sendMailRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<MailResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<MailResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListMailRequestDTO listMailRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<MailResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageMailRequestDTO pageMailRequestDTO
    );
}
