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

    private String rootFileId = "ROOT_FILE_PATH"; // 文件存储根节点id

    private String umsFileId = "UMS_FILE_PATH"; // ums文件存储根节点id

    private String umsFilePath = "ums"; // ums文件存储根节点id

    private String umsAppId = "UMS"; // ums应用id

    private String umsAppUserId = "10001"; // ums管理员id

    public String getRootFileId() {
        return rootFileId;
    }

    public void setRootFileId(String rootFileId) {
        this.rootFileId = rootFileId;
    }

    public String getUmsFileId() {
        return umsFileId;
    }

    public void setUmsFileId(String umsFileId) {
        this.umsFileId = umsFileId;
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
