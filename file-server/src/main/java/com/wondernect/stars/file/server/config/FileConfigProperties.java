package com.wondernect.stars.file.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RBACConfigProperties
 * Author: chenxun
 * Date: 2020-09-20 10:10
 * Description:
 */
@Component
@Primary
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
@ConfigurationProperties(prefix = "wondernect.stars.file")
public class FileConfigProperties implements Serializable {

    private static final long serialVersionUID = 3260656889942519185L;

    private String rootFilePathId = "ROOT_FILE_PATH"; // 文件存储根节点id

    private String umsFilePathId = "UMS_FILE_PATH"; // ums文件存储根节点id

    private String umsFilePath = "ums"; // ums文件存储根节点id

    private String umsAppId = "UMS"; // ums应用id

    private String umsAppUserId = "10001"; // ums管理员id

    public String getRootFilePathId() {
        return rootFilePathId;
    }

    public void setRootFilePathId(String rootFilePathId) {
        this.rootFilePathId = rootFilePathId;
    }

    public String getUmsFilePathId() {
        return umsFilePathId;
    }

    public void setUmsFilePathId(String umsFilePathId) {
        this.umsFilePathId = umsFilePathId;
    }

    public String getUmsFilePath() {
        return umsFilePath;
    }

    public void setUmsFilePath(String umsFilePath) {
        this.umsFilePath = umsFilePath;
    }

    public String getUmsAppId() {
        return umsAppId;
    }

    public void setUmsAppId(String umsAppId) {
        this.umsAppId = umsAppId;
    }

    public String getUmsAppUserId() {
        return umsAppUserId;
    }

    public void setUmsAppUserId(String umsAppUserId) {
        this.umsAppUserId = umsAppUserId;
    }
}
