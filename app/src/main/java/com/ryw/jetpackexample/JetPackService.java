package com.ryw.jetpackexample;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JetPackService {

    @POST("greet")
    @FormUrlEncoded
    Call<JetPackRespBean> greetFormUrl(@FieldMap Map<String, String> map);

    @POST("greet")
    Call<JetPackRespBean> greetJson(@Body GreetBean insertBean);

}
