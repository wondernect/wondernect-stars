package com.wondernect.stars.user.excel;

import com.wondernect.elements.easyoffice.excel.ESExcelImportDataHandler;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.ESExcelUtils;
import com.wondernect.stars.user.dto.UserResponseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserExportHandler
 * Author: chenxun
 * Date: 2020-11-12 15:15
 * Description: 用户导入handler
 */
@Service
public class UserImportDataHandler extends ESExcelImportDataHandler {

    public Map<String, String> getDataPropertyMapping() {
        Map<String, String> dictionary = new HashMap<>();
        List<ESExcelItem> excelItemList = ESExcelUtils.getAllEntityExcelItem(UserResponseDTO.class);
        if (CollectionUtils.isNotEmpty(excelItemList)) {
            for (ESExcelItem excelItem : excelItemList) {
                dictionary.put(excelItem.getTitle(), excelItem.getName());
            }
        }
        return dictionary;
    }
}
