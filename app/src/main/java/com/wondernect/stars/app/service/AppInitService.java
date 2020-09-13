package com.wondernect.stars.app.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.app.config.AppConfigProperties;
import com.wondernect.stars.app.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppInitService
 * Author: chenxun
 * Date: 2020-09-13 23:34
 * Description:
 */
@Service
public class AppInitService implements ApplicationListener<WondernectBootEvent> {

    @Autowired
    private AppConfigProperties appConfigProperties;

    @Autowired
    private AppService appService;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                App app = appService.findEntityById(appConfigProperties.getAppId());
                if (ESObjectUtils.isNull(app)) {
                    app = new App(
                            appConfigProperties.getAppName(),
                            appConfigProperties.getSecret(),
                            appConfigProperties.getLogo(),
                            appConfigProperties.getBrief(),
                            appConfigProperties.getWebsite(),
                            appConfigProperties.getUserId()
                    );
                    app.setId(appConfigProperties.getAppId());
                    appService.saveEntity(app);
                }
                break;
            }
            default:
                break;
        }
    }
}
