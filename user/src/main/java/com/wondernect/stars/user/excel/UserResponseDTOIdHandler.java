package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 头像导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOIdHandler extends ESExcelItemHandler<String> {

    public UserResponseDTOIdHandler() {
        super("用户id", 0);
    }

    public UserResponseDTOIdHandler(int itemOrder) {
        super("用户id", itemOrder);
    }

    public UserResponseDTOIdHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "id";
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