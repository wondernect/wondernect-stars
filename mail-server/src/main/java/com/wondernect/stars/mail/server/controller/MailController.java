package com.wondernect.stars.mail.server.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.mail.client.util.MailSendResult;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.mail.dto.*;
import com.wondernect.stars.mail.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 邮件接口
 *
 * @author 王威 2020-11-23 11:21:48
 **/
@RequestMapping(value = "/v1.0/wondernect/mail")
@RestController
@Validated
@Api(tags = "邮件接口")
public class MailController {

    @Autowired
    private MailService mailService;

    @ApiOperation(value = "发送邮件", httpMethod = "POST")
    @PostMapping(value = "/send")
    public BusinessData<MailSendResult> send(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SendMailRequestDTO sendMailRequestDTO
    ) {
        return new BusinessData<>(mailService.send(sendMailRequestDTO));
    }

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<MailResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMailRequestDTO saveMailRequestDTO
    ) {
        return new BusinessData<>(mailService.create(saveMailRequestDTO));
    }

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<MailResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMailRequestDTO saveMailRequestDTO
    ) {
        return new BusinessData<>(mailService.update(id, saveMailRequestDTO));
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        mailService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<MailResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(mailService.findById(id));
    }

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<MailResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListMailRequestDTO listMailRequestDTO
    ) {
        return new BusinessData<>(mailService.list(listMailRequestDTO));
    }

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<MailResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageMailRequestDTO pageMailRequestDTO
    ) {
        return new BusinessData<>(mailService.page(pageMailRequestDTO));
    }
}