package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 角色类型导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:20
 **/
public class UserResponseDTORoleTypeIdHandler extends ESExcelItemHandler<String> {

    public UserResponseDTORoleTypeIdHandler() {
        super("角色类型id", 0);
    }

    public UserResponseDTORoleTypeIdHandler(int itemOrder) {
        super("角色类型id", itemOrder);
    }

    public UserResponseDTORoleTypeIdHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "roleTypeId";
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