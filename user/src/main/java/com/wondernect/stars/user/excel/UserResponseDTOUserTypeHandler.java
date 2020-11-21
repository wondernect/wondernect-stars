package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.stars.user.em.UserType;

/**
 * 用户类型导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:22
 **/
public class UserResponseDTOUserTypeHandler extends ESExcelItemHandler<UserType> {

    public UserResponseDTOUserTypeHandler() {
        super("用户类型", 0);
    }

    public UserResponseDTOUserTypeHandler(int itemOrder) {
        super("用户类型", itemOrder);
    }

    public UserResponseDTOUserTypeHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "userType";
    }

    @Override
    public Object handleExcelExportItemObject(UserType object) {
        if (ESObjectUtils.isNotNull(object)) {
            switch (object) {
                case LOCAL:
                {
                    return "本地用户";
                }
                case THIRD:
                {
                    return "第三方用户";
                }
                default:
                {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    @Override
    public UserType handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            switch (object.toString()) {
                case "本地用户":
                {
                    return UserType.LOCAL;
                }
                case "第三方用户":
                {
                    return UserType.THIRD;
                }
                default:
                {
                    return null;
                }
            }
        } else {
            return null;
        }
    }
}