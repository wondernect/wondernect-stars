package com.wondernect.stars.user.excel;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.stars.user.em.Gender;

/**
 * 性别导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:20
 **/
public class UserResponseDTOGenderHandler extends ESExcelItemHandler<Gender> {

    public UserResponseDTOGenderHandler() {
        super("性别", 0);
    }

    public UserResponseDTOGenderHandler(int itemOrder) {
        super("性别", itemOrder);
    }

    public UserResponseDTOGenderHandler(String itemTitle, int itemOrder) {
        super(itemTitle, itemOrder);
    }

    @Override
    public String itemName() {
        return "gender";
    }

    @Override
    public Object handleExcelExportItemObject(Gender object) {
        if (ESObjectUtils.isNotNull(object)) {
            switch (object) {
                case MALE:
                {
                    return "男";
                }
                case FEMALE:
                {
                    return "女";
                }
                case UNKNOWN:
                {
                    return "未知";
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
    public Gender handleExcelImportItemObject(Object object) {
        if (ESObjectUtils.isNotNull(object)) {
            switch (object.toString()) {
                case "男":
                {
                    return Gender.MALE;
                }
                case "女":
                {
                    return Gender.FEMALE;
                }
                case "未知":
                {
                    return Gender.UNKNOWN;
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