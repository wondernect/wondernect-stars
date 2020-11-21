package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 是否可用导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOEnableHandler extends ESExcelItemHandler<Boolean> {

    public UserResponseDTOEnableHandler() {
        super("是否可用", 0);
    }

    public UserResponseDTOEnableHandler(int itemOrder) {
        super("是否可用", itemOrder);
    }

    public UserResponseDTOEnableHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "enable";
    }

    @Override
    public Object handleExcelExportItemObject(Boolean object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (object) {
                return "是";
            } else {
                return "否";
            }
        } else {
            return null;
        }
    }

    @Override
    public Boolean handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            if (ESStringUtils.equals("是", object.toString())) {
                return true;
            } else {
                return false;
            }
        } else {
            return null;
        }
    }
}