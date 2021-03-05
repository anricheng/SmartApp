package com.capgemini.http;

import android.content.Context;

import com.capgemini.util.HttpsUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RetrofitManager 负责创建Service 实例
 */

public class RetrofitManager {
    private Context context;
    private OkHttpClient mOkHttpClient;
    private HashMap<String, Retrofit> retrofitHashMap;
    private HashMap<String, Map<Class, Object>> mServices;


    public RetrofitManager(Context context) {
        this.context = context;
        retrofitHashMap = new HashMap<>();
        mServices = new HashMap<>();
        initRetrofitClient();
    }

    private void initRetrofitClient() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .readTimeout(8, TimeUnit.SECONDS)
                .connectTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(new HttpInterceptor())
                .addInterceptor(new ChuckInterceptor(context))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        }
        mOkHttpClient = builder.build();
    }

    private Retrofit getRetrofit(String baseUrl) {
        if (retrofitHashMap.containsKey(baseUrl)) {
            return retrofitHashMap.get(baseUrl);
        }
        Gson gson = new GsonBuilder().setLenient().disableHtmlEscaping().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        retrofitHashMap.put(baseUrl, retrofit);
        return retrofit;
    }


    public <T> T createApi(Class<T> clazz, String url) {

        Retrofit retrofit = getRetrofit(url);
        T api;
        if (mServices.containsKey(url)) {
            Map<Class, Object> objectMap = mServices.get(url);
            if (objectMap == null) {
                objectMap = new HashMap<>();
                api = retrofit.create(clazz);
                objectMap.put(clazz, api);
                mServices.put(url, objectMap);
            } else {
                if (objectMap.containsKey(clazz)) {
                    api = (T) objectMap.get(clazz);
                } else {
                    api = retrofit.create(clazz);
                    objectMap.put(clazz, api);
                }
            }
        } else {
            HashMap<Class, Object> objectMap = new HashMap<>();
            api = retrofit.create(clazz);
            objectMap.put(clazz, api);
            mServices.put(url, objectMap);
        }
        return api;
    }
}
