package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 坐标(地理位置)导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOLocationHandler extends ESExcelItemHandler<String> {

    public UserResponseDTOLocationHandler() {
        super("地理位置", 0);
    }

    public UserResponseDTOLocationHandler(int itemOrder) {
        super("地理位置", itemOrder);
    }

    public UserResponseDTOLocationHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "location";
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