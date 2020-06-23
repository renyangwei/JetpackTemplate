package com.ryw.jetpackexample;

import android.util.Log;

import com.ryw.mapo.Mapo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private Retrofit retrofit;

    private JetPackService jetPackService;

    public RetrofitInstance(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://172.21.0.189:8888")
                // 要配置Gson
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jetPackService = retrofit.create(JetPackService.class);
    }

    public void loadFormUrl(GreetBean greetBean, Callback<JetPackRespBean> callback) {
        Mapo mapo = new Mapo();
        Map<String, String> map = mapo.toMap(greetBean);
        Log.e("Jetpack", mapo.toFormUrlString(map));
        Call<JetPackRespBean> call = jetPackService.greetFormUrl(map);
        call.enqueue(callback);
    }

    public void loadJson(GreetBean greetBean, Callback<JetPackRespBean> callback) {
        Call<JetPackRespBean> call = jetPackService.greetJson(greetBean);
        call.enqueue(callback);
    }
}
