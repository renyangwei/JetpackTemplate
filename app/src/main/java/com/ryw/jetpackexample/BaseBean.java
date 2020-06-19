package com.ryw.jetpackexample;

import com.ryw.mapo.anotations.SerializedName;

public class BaseBean {

    @SerializedName("app_id")
    public String appId;

    @SerializedName("app_key")
    public String appKey;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
