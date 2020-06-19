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

    private MutableLiveData<String> insertResp;

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

    public MutableLiveData<String> getInsertResp() {
        if (insertResp == null) {
            insertResp = new MutableLiveData<>();

        }
        loadData();
        return insertResp;
    }


    public void loadData() {
        // 使用网络数据
        InsertBean insertBean = new InsertBean();
        insertBean.setFirstName("你好 *$#!^adsf");
        insertBean.setLastName("世界");
        insertBean.setAppId("123456");
        insertBean.setAppKey("dhaofanohkhjljk");
        try {
            Mapo mapo = new Mapo();
            Map<String, String> map = mapo.toMap(insertBean);
            Log.e("Jetpack", mapo.toFormUrlEncoded(map));
            Call<JetPackRespBean> call = jetPackService.insert(map);
            call.enqueue(new Callback<JetPackRespBean>() {
                @Override
                public void onResponse(Call<JetPackRespBean> call, Response<JetPackRespBean> response) {
                    String data = response.body().getData();
                    if (data != null) {
                        insertResp.setValue(data);
                    } else {
                        insertResp.setValue("");
                    }
                }

                @Override
                public void onFailure(Call<JetPackRespBean> call, Throwable t) {
                    insertResp.setValue(t.getLocalizedMessage());
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
