package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 邮箱导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOEmailHandler extends ESExcelItemHandler<String> {

    public UserResponseDTOEmailHandler() {
        super("邮箱", 0);
    }

    public UserResponseDTOEmailHandler(int itemOrder) {
        super("邮箱", itemOrder);
    }

    public UserResponseDTOEmailHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "email";
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