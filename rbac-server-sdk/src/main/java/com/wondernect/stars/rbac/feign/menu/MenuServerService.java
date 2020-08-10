package com.wondernect.stars.rbac.feign.menu;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/10 14:30
 * @Version 1.0
 */

@Service
public class MenuServerService {

    @Autowired
    private MenuFeignClient menuFeignClient;

    public MenuResponseDTO create(SaveMenuRequestDTO saveMenuRequestDTO){
        BusinessData<MenuResponseDTO> businessData = menuFeignClient.create(saveMenuRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public MenuResponseDTO update(String id,SaveMenuRequestDTO saveMenuRequestDTO){
        BusinessData<MenuResponseDTO> businessData = menuFeignClient.update(id,saveMenuRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean delete(String id) {
        BusinessData businessData = menuFeignClient.delete(id);
        return businessData.success();
    }

    public MenuResponseDTO get(String id){
        BusinessData<MenuResponseDTO> businessData = menuFeignClient.get(id);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<MenuResponseDTO> list(ListMenuRequestDTO listMenuRequestDTO){
        BusinessData<List<MenuResponseDTO>> businessData = menuFeignClient.list(listMenuRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<MenuResponseDTO> page(PageMenuRequestDTO pageMenuRequestDTO){
        BusinessData<PageResponseData<MenuResponseDTO>> businessData = menuFeignClient.page(pageMenuRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public MenuTreeResponseDTO tree(String rootMenuCode){
        BusinessData<MenuTreeResponseDTO> businessData = menuFeignClient.tree(rootMenuCode);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
