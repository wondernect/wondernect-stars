package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 个性签名导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:21
 **/
public class UserResponseDTORemarkHandler extends ESExcelItemHandler<String> {

    public UserResponseDTORemarkHandler() {
        super("个性签名", 0);
    }

    public UserResponseDTORemarkHandler(int itemOrder) {
        super("个性签名", itemOrder);
    }

    public UserResponseDTORemarkHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "remark";
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