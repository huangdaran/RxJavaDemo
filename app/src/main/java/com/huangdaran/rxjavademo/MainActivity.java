package com.huangdaran.rxjavademo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.huangdaran.rxjavademo.dao.GetApi;
import com.huangdaran.rxjavademo.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

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
        getApi();
    }

    private void getUser(){
        Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {

            }
        }).doOnNext(new Action1<User>() {
            @Override
            public void call(User user) {

            }
        }).subscribe(new Subscriber<User>() {
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
        Log.i("onNext","code == 0000000");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.ttj2015.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetApi api = retrofit.create(GetApi.class);
        Call<ResponseBody> call = api.callToken("12225454","android");
        call.enqueue(new retrofit2.Callback<ResponseBody>(){
            @Override
            public void onResponse(Call call, Response response) {
                int code  = response.code();
                Log.i("onNext","code == "+code);
                if(code == 200){
                    Log.i("onNext","msg == "+response.body().toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.i("onNext","Throwable == "+t.getMessage());
            }
        } );
    }
}
