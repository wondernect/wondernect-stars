package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 手机导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:21
 **/
public class UserResponseDTOMobileHandler extends ESExcelItemHandler<String> {

    public UserResponseDTOMobileHandler() {
        super("手机", 0);
    }

    public UserResponseDTOMobileHandler(int itemOrder) {
        super("手机", itemOrder);
    }

    public UserResponseDTOMobileHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "mobile";
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