package com.huangdaran.rxjavademo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.huangdaran.rxjavademo.dao.GetApi;
import com.huangdaran.rxjavademo.inter.IMainView;
import com.huangdaran.rxjavademo.model.TokenModel;
import com.huangdaran.rxjavademo.model.User;
import com.huangdaran.rxjavademo.presenter.IMainPresenter;

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

public class MainActivity extends AppCompatActivity implements IMainView{
    private TextView tv_user;
    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_user = (TextView)findViewById(R.id.tv_user);
        presenter = new IMainPresenter(this);
        presenter.getUserData("1027");
    }



    @Override
    public void showToast(String msg) {
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTextView(String msg) {
        tv_user.setText(msg);
    }
}
