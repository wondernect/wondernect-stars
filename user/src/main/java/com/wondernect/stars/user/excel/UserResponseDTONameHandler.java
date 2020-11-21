package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 姓名导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTONameHandler extends ESExcelItemHandler<String> {

    public UserResponseDTONameHandler() {
        super("姓名", 0);
    }

    public UserResponseDTONameHandler(int itemOrder) {
        super("姓名", itemOrder);
    }

    public UserResponseDTONameHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "name";
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