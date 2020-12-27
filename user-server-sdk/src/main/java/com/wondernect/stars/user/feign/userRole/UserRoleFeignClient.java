package com.wondernect.stars.user.feign.userRole;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/12 11:21
 * @Version 1.0
 */
@FeignClient(name = "${wondernect.stars.user.feign.name}", url = "${wondernect.stars.user.feign.url}", path = "/v1/wondernect/user/role", configuration = WondernectFeignConfiguration.class)
public interface UserRoleFeignClient {

    @ApiOperation(value = "新增", httpMethod = "POST")
    @PostMapping(value = "/add")
    public BusinessData create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) UserRoleRequestDTO userRoleRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) UserRoleRequestDTO userRoleRequestDTO
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/detail")
    public BusinessData<UserRoleResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @RequestParam(value = "user_id", required = false) String userId,
            @ApiParam(required = true) @NotBlank(message = "角色id不能为空") @RequestParam(value = "role_id", required = false) String roleId
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<UserRoleResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListUserRoleRequestDTO listUserRoleRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<UserRoleResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageUserRoleRequestDTO pageUserRoleRequestDTO
    );
}
