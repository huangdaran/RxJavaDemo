package com.huangdaran.rxjavademo.dao;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface GetApi {
    @GET("NewApi/Apptoken/index")
//    public Call<ResponseBody> callToken();
//    public Call<ResponseBody> callToken(@Query("uid") String id,@Query("api_version") String api);
    public Call<ResponseBody> callToken(@Query("appid")String app_id,@Query("app_platform")String app_platform);



}
