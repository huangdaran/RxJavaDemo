package com.huangdaran.rxjavademo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huangdaran.rxjavademo.dao.GetApi;
import com.huangdaran.rxjavademo.model.TokenModel;
import com.huangdaran.rxjavademo.model.User;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for(int i = 0;i < 10;i++){
                    subscriber.onNext(""+i);
                }
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
//                Toast.makeText(MainActivity.this,"完了",Toast.LENGTH_SHORT).show();
//                getString();
//                getUserName();

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
//                Toast.makeText(MainActivity.this,"s",Toast.LENGTH_SHORT).show();
                Log.i("onNext",s);
            }
        });*/
//        getApi();
        getStringToken();
//        getStringTokenModel();
    }

    private void getUser(){
        Log.i("onNext","code == 0000000");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ttj2015.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        GetApi api = retrofit.create(GetApi.class);
        api.getUsers("111").doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(User user) {

            }
        });
    }
    private void getString(){
        Observable.just("你好啊").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return "黄达然  "+s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getUserName(){
        User u[] = new User[]{new User("小然","男"),new User("小名","男"),new User("小红","女")};
        Observable.from(u).flatMap(new Func1<User, Observable<String>>() {
            @Override
            public Observable<String> call(User user) {
                return Observable.just(user.getName());
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("onNext",s);
            }
        });
    }

    private void getApi(){
        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("User-Agent", "Retrofit-Sample-App").build();
                return chain.proceed(newRequest);
            }
        };
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        builder.cache(new Cache(new File("C:\\okhttp"),10*1024*1024)) ;
        OkHttpClient client = builder.build();

        Log.i("onNext","code == 0000000");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ttj2015.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(client)
                .build();
        GetApi api = retrofit.create(GetApi.class);
        Call<String> call = api.callToken("12225454","android");
        call.enqueue(new retrofit2.Callback<String>(){
            @Override
            public void onResponse(Call call, Response response) {
                int code  = response.code();
                Log.i("onNext","code == "+code);
                if(code == 200){
                    Log.i("onNext","msg == "+response.body());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("onNext","Throwable == "+t.getMessage());
            }
        } );
    }
    public void getStringToken(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ttj2015.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetApi api = retrofit.create(GetApi.class);
        api.getTokenString("12225454","android").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TokenModel>() {
                    @Override
                    public void onCompleted() {
                        Log.i("onNext","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("onNext","onError == "+e.getMessage());
                    }

                    @Override
                    public void onNext(TokenModel model) {
                        Log.i("onNext","onNext == "+model.token_data);
                    }
                });
    }
    public void getStringTokenModel(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ttj2015.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetApi api = retrofit.create(GetApi.class);
        api.getTokenModel("12225454","android").enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                Log.i("onNext","onResponse == "+response.body().token_data);
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t) {

            }
        });
    }
}
