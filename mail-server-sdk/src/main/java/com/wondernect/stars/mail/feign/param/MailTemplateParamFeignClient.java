package com.wondernect.stars.mail.feign.param;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.mail.dto.param.ListMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.MailTemplateParamResponseDTO;
import com.wondernect.stars.mail.dto.param.PageMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.dto.param.SaveMailTemplateParamRequestDTO;
import com.wondernect.stars.mail.feign.config.WondernectMailFeignConfiguration;
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

@FeignClient(name = "${wondernect.stars.mail.feign.name}", url = "${wondernect.stars.mail.feign.url}", path = "/v1/wondernect/mail/template_param", configuration = WondernectMailFeignConfiguration.class)
public interface MailTemplateParamFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<MailTemplateParamResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<MailTemplateParamResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMailTemplateParamRequestDTO saveMailTemplateParamRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<MailTemplateParamResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<MailTemplateParamResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListMailTemplateParamRequestDTO listMailTemplateParamRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<MailTemplateParamResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageMailTemplateParamRequestDTO pageMailTemplateParamRequestDTO
    );
}
