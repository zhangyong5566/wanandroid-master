package com.zhang.wanandroiod.http;

import com.zhang.wanandroiod.MyApplication;
import com.zhang.wanandroiod.http.service.AddCookiesInterceptor;
import com.zhang.wanandroiod.http.service.CacheControlInterceptor;
import com.zhang.wanandroiod.http.service.HttpLoggingInterceptor;
import com.zhang.wanandroiod.http.service.SaveCookiesInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/2/27.
 */

public class PlayAndroidHttpFactory {

    private static PlayAndroidHttpFactory playAndroidHttpFactory;
    private final Cache mCache;

    private PlayAndroidHttpFactory() {
        mCache = new Cache(new File(MyApplication.getApplictaion().getCacheDir(), "HttpCache"),
                1024 * 1024 * 10);
    }

    public static PlayAndroidHttpFactory getInstence() {
        if (playAndroidHttpFactory == null) {
            synchronized (PlayAndroidHttpFactory.class) {
                if (playAndroidHttpFactory == null) {
                    playAndroidHttpFactory = new PlayAndroidHttpFactory();
                }
            }
        }
        return playAndroidHttpFactory;
    }

    public <T> T createService(Class<T> serviceClass, String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                // 添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                // 添加Retrofit到RxJava的转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                .client(getOkHttpClient())
                .build();
        return retrofit.create(serviceClass);
    }

    private final static long DEFAULT_TIMEOUT = 10;

    private OkHttpClient getOkHttpClient() {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        httpClientBuilder.addInterceptor(new HttpLoggingInterceptor());
//        httpClientBuilder.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request()
//                        .newBuilder()
//                        .addHeader("User-Agent", "Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52")
//                        .build();
//                return chain.proceed(request);
//            }
//
//        });
        httpClientBuilder.cache(mCache);
        httpClientBuilder.addInterceptor(new CacheControlInterceptor());
        httpClientBuilder.addInterceptor(new AddCookiesInterceptor(MyApplication.getApplictaion()));
        httpClientBuilder.addInterceptor(new SaveCookiesInterceptor(MyApplication.getApplictaion()));
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        return httpClientBuilder.build();
    }


}
