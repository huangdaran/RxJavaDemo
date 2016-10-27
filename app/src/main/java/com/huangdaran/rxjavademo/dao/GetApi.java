package com.huangdaran.rxjavademo.dao;

import com.huangdaran.rxjavademo.model.TokenModel;
import com.huangdaran.rxjavademo.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface GetApi {
    @GET("NewApi/Apptoken/index")
//    public Call<ResponseBody> callToken();
//    public Call<ResponseBody> callToken(@Query("uid") String id,@Query("api_version") String api);
    public Call<String> callToken(@Query("appid")String app_id,@Query("app_platform")String app_platform);

    @GET("NewApi/user/userxx")
    public Observable<User> getUsers(@Query("id")String uid,@Query("ttj_token")String ttj_token,@Query("api_version")String api_version);
    @GET("NewApi/Apptoken/index")
    public Observable<String> getToken(@Query("appid")String app_id,@Query("app_platform")String app_platform);

    @GET("NewApi/Apptoken/index")
    public Call<TokenModel> getTokenModel(@Query("appid")String app_id,@Query("app_platform")String app_platform);
    @GET("NewApi/Apptoken/index")
    public Observable<TokenModel> getTokenString(@Query("appid")String app_id,@Query("app_platform")String app_platform);
}
