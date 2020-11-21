package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 角色导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:21
 **/
public class UserResponseDTORoleIdHandler extends ESExcelItemHandler<String> {

    public UserResponseDTORoleIdHandler() {
        super("角色id", 0);
    }

    public UserResponseDTORoleIdHandler(int itemOrder) {
        super("角色id", itemOrder);
    }

    public UserResponseDTORoleIdHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "roleId";
    }

    @Override
    public Object handleExcelExportItemObject(String object) {
        return object;
    }

    @Override
    public String handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            return object.toString();
        } else {
            return null;
        }
    }
}