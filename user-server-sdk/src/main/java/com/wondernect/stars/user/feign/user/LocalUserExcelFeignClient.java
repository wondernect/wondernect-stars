package com.wondernect.stars.user.feign.user;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.feign.config.WondernectUserFeignConfiguration;
import feign.Response;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author:王威
 * @Date: 2020/8/12 11:21
 * @Version 1.0
 */
@FeignClient(name = "${wondernect.stars.user.feign.name}", url = "${wondernect.stars.user.feign.url}", path = "/v1/wondernect/user", configuration = {WondernectUserFeignConfiguration.class, LocalUserExcelFeignClient.MultipartSupportConfig.class})
public interface LocalUserExcelFeignClient {

    @Configuration
    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

    @ApiOperation(value = "初始化本地用户导入导出item", httpMethod = "POST")
    @PostMapping(value = "/init_local_user_item")
    public BusinessData initLocalUserExcelItem(
            @ApiParam(required = false) @RequestParam(value = "force_update", required = false) Boolean forceUpdate
    );

    @ApiOperation(value = "本地用户导出", httpMethod = "POST")
    @PostMapping(value = "/excel_data_export")
    public Response excelDataExport(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId,
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListUserRequestDTO listUserRequestDTO
    );

    @ApiOperation(value = "本地用户导入", httpMethod = "POST")
    @PostMapping(value = "/excel_data_import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response excelDataImport(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId,
            @ApiParam(required = true) @RequestPart(value = "file", required = false) MultipartFile file
    );

    @ApiOperation(value = "本地用户导入模板下载", httpMethod = "GET")
    @GetMapping(value = "/excel_data_import_model")
    public Response excelDataImportModel(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId
    );
}
