package com.wondernect.stars.file.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.file.model.LocalFilePath;
import com.wondernect.stars.file.service.localfilepath.LocalFilePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: FileInitService
 * Author: chenxun
 * Date: 2020-06-27 02:25
 * Description:
 */
@Service
public class FileInitService implements ApplicationListener<WondernectBootEvent> {

    @Autowired
    private LocalFilePathService localFilePathService;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                LocalFilePath localFilePath = localFilePathService.findEntityById("0");
                if (ESObjectUtils.isNull(localFilePath)) {
                    // 初始化根节点
                    localFilePath = new LocalFilePath(
                            "文件存储根节点",
                            "文件存储根节点",
                            "",
                            "",
                            "-1"
                    );
                    localFilePath.setId("0");
                    localFilePathService.save(localFilePath);
                    // 初始化测试节点
                    localFilePathService.save(
                            new LocalFilePath(
                                    "文件存储测试节点",
                                    "文件存储测试节点",
                                    "demo",
                                    "demo",
                                    "0"
                            )
                    );
                }
                break;
            }
            default:
                break;
        }
    }
}
