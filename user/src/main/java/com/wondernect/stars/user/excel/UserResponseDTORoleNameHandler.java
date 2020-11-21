package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 角色名称导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTORoleNameHandler extends ESExcelItemHandler<String> {

    public UserResponseDTORoleNameHandler() {
        super("角色名称", 0);
    }

    public UserResponseDTORoleNameHandler(int itemOrder) {
        super("角色名称", itemOrder);
    }

    public UserResponseDTORoleNameHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "roleName";
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