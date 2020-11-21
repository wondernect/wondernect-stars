package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESDateTimeUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;

/**
 * 头像导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOCreateTimeHandler extends ESExcelItemHandler<Long> {

    public UserResponseDTOCreateTimeHandler() {
        super("创建时间", 0);
    }

    public UserResponseDTOCreateTimeHandler(int itemOrder) {
        super("创建时间", itemOrder);
    }

    public UserResponseDTOCreateTimeHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "createTime";
    }

    @Override
    public Object handleExcelExportItemObject(Long object) {
        if (ESObjectUtils.isNotNull(object)) {
            return ESDateTimeUtils.formatDate(object, "yyyy-MM-dd");
        } else {
            return null;
        }
    }

    @Override
    public Long handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            return ESDateTimeUtils.formatDate(object.toString(), "yyyy-MM-dd");
        } else {
            return null;
        }
    }
}