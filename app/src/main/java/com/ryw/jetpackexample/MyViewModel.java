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

    private Retrofit retrofit;

    private JetPackService jetPackService;

    public MyViewModel(Application application) {
        super(application);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.21.0.189:8888")
                // 要配置Gson
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jetPackService = retrofit.create(JetPackService.class);
    }

    public MutableLiveData<String> greetFormUrl() {
        if (greetResp == null) {
            greetResp = new MutableLiveData<>();

        }
        loadFormUrl();
        return greetResp;
    }


    public void loadFormUrl() {
        // 使用网络数据
        GreetBean insertBean = new GreetBean();
        insertBean.setFirstName("你好 *$#!^adsf");
        insertBean.setLastName("formurl");
        insertBean.setAppId("123456");
        insertBean.setAppKey("dhaofanohkhjljk");
        Mapo mapo = new Mapo();
        Map<String, String> map = mapo.toMap(insertBean);
        Log.e("Jetpack", mapo.toFormUrlString(map));
        Call<JetPackRespBean> call = jetPackService.greetFormUrl(map);
        call.enqueue(new Callback<JetPackRespBean>() {
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

    public MutableLiveData<String> greetJson() {
        if (greetResp == null) {
            greetResp = new MutableLiveData<>();

        }
        loadJson();
        return greetResp;
    }

    public void loadJson() {
        // 使用网络数据
        GreetBean insertBean = new GreetBean();
        insertBean.setFirstName("你好 *$#!^adsf");
        insertBean.setLastName("json");
        insertBean.setAppId("123456");
        insertBean.setAppKey("dhaofanohkhjljk");
        Call<JetPackRespBean> call = jetPackService.greetJson(insertBean);
        call.enqueue(new Callback<JetPackRespBean>() {
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
