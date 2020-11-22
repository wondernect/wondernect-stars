package com.wondernect.stars.user.excel;

import com.wondernect.elements.easyoffice.excel.ESExcelIDItemHandler;
import com.wondernect.stars.user.em.Gender;

import java.util.Map;

/**
 * 性别导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:20
 **/
public class UserResponseDTOGenderHandler extends ESExcelIDItemHandler<Gender> {

    public UserResponseDTOGenderHandler(String itemName, String itemTitle, int itemOrder, Map<Gender, String> dictionary) {
        super(itemName, itemTitle, itemOrder, dictionary);
    }
}