package org.gu.mobile.android.data.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Config {
    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("device.name")
    private String deviceName;

    @JsonProperty("platform.name")
    private String platformName;

    @JsonProperty("platform.version")
    private String platformVersion;

    @JsonProperty("automation.name")
    private String automationName;

    @JsonProperty("app.path")
    private String appPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getAutomationName() {
        return automationName;
    }

    public void setAutomationName(String automationName) {
        this.automationName = automationName;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getFullName() {
        return String.format("%s (%s - %s %s)", name, deviceName, platformName, platformVersion);
    }
}
