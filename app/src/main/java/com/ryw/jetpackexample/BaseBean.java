package com.ryw.jetpackexample;


//import com.google.gson.annotations.SerializedName;

import com.ryw.mapo.anotations.Expose;
import com.ryw.mapo.anotations.SerializedName;

public class BaseBean {

    @SerializedName("app_id")
    public String appId;

    @SerializedName("app_key")
    @Expose(serialize = false)
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
