package com.capgemini.http;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.PhoneUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpInterceptor implements Interceptor {

    public HttpInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        RequestBody body = originRequest.body();
        Request.Builder builder = originRequest.newBuilder();

        //添加公共header
        Map<String, String> headers = getHeaders();
        Set<String> keys = headers.keySet();
        for (String headerKey : keys) {
            builder.addHeader(headerKey, Objects.requireNonNull(headers.get(headerKey))).build();
        }
        return chain.proceed(builder.build());
    }


    @SuppressLint("MissingPermission")
    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Uuid", PhoneUtils.getIMEI());
        headers.put("x-client-id", "APP");
        headers.put("versionCode", AppUtils.getAppVersionCode() + "");
        headers.put("MAC", DeviceUtils.getMacAddress());
        headers.put("DeviceID", PhoneUtils.getDeviceId());
        return headers;
    }
}
