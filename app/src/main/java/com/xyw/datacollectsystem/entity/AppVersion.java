package com.xyw.datacollectsystem.entity;

/**
 * Created by 31429 on 2017/9/20.
 * <p>
 * 检查软件更新时的实体对象
 */

public class AppVersion {

    private String version = null;
    private String url = null;
    private String description = null;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
