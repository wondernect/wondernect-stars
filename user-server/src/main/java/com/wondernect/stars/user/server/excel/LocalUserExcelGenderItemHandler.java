package com.wondernect.stars.user.server.excel;

import com.wondernect.elements.easyoffice.excel.handler.ESExcelEnumItemHandler;
import com.wondernect.stars.user.em.Gender;

import java.util.Map;

/**
 * 性别导入导出item handler
 *
 * @author chenxun 2020-11-20 15:42:20
 **/
public class LocalUserExcelGenderItemHandler extends ESExcelEnumItemHandler<Gender> {

    public LocalUserExcelGenderItemHandler(String itemName, String itemTitle, int itemOrder, Map<Gender, String> dictionary) {
        super(itemName, itemTitle, itemOrder, dictionary);
    }
}