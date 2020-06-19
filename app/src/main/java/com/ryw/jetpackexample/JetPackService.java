package com.ryw.jetpackexample;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JetPackService {

    @POST("insert")
    @FormUrlEncoded
    Call<JetPackRespBean> insert(@FieldMap Map<String, String> map);

}
