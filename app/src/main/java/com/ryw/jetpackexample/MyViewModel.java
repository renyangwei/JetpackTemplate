package com.ryw.jetpackexample;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.ryw.mapo.Mapo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyViewModel extends AndroidViewModel {

    private MutableLiveData<String> greetResp;

    private RetrofitInstance retrofitInstance;

    public MyViewModel(Application application) {
        super(application);
        retrofitInstance = new RetrofitInstance();
    }

    public MutableLiveData<String> greetFormUrl() {
        if (greetResp == null) {
            greetResp = new MutableLiveData<>();

        }
        loadFormUrl();
        return greetResp;
    }


    public void loadFormUrl() {
        GreetBean greetBean = new GreetBean();
        greetBean.setFirstName("你好 *$#!^adsf");
        greetBean.setLastName("formurl");
        greetBean.setAppId("123456");
        greetBean.setAppKey("dhaofanohkhjljk");
        retrofitInstance.loadFormUrl(greetBean, new Callback<JetPackRespBean>() {
            @Override
            public void onResponse(Call<JetPackRespBean> call, Response<JetPackRespBean> response) {
                String data = response.body().getData();
                if (data != null) {
                    greetResp.setValue(data);
                } else {
                    greetResp.setValue("");
                }
            }

            @Override
            public void onFailure(Call<JetPackRespBean> call, Throwable t) {
                greetResp.setValue(t.getLocalizedMessage());
            }
        });
    }


    public void loadJson() {
        // 使用网络数据
        GreetBean greetBean = new GreetBean();
        greetBean.setFirstName("你好 *$#!^adsf");
        greetBean.setLastName("json");
        greetBean.setAppId("123456");
        greetBean.setAppKey("dhaofanohkhjljk");
        retrofitInstance.loadJson(greetBean, new Callback<JetPackRespBean>() {
            @Override
            public void onResponse(Call<JetPackRespBean> call, Response<JetPackRespBean> response) {
                String data = response.body().getData();
                if (data != null) {
                    greetResp.setValue(data);
                } else {
                    greetResp.setValue("");
                }
            }

            @Override
            public void onFailure(Call<JetPackRespBean> call, Throwable t) {
                greetResp.setValue(t.getLocalizedMessage());
            }
        });
    }


}
