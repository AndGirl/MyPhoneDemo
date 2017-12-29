package com.ybj.phonehelp.http;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 杨阳洋 on 2017/12/25.
 * okHttp + retrofit2 + rxJava简单封装
 */

public class OkHttpManager {

    /**
     * 获取OkClient并对其进行基本配置
     * @return
     */
    public OkHttpClient getOkHttpClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        //开发模式记录整个body，否则只记录基本信息如返回200，http协议版本等
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //如果使用到HTTPS，我们需要创建SSLSocketFactory，并设置到client
        X509TrustManager trustManager = null;
        SSLSocketFactory mSslSocketFactory = null;
        try {
            trustManager = defaultTrustManager();
            mSslSocketFactory = defaultSslSocketFactory(trustManager);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                //.addInterceptor(new HeadInterceptor())
                .addInterceptor(loggingInterceptor)
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
                .sslSocketFactory(mSslSocketFactory,trustManager)
                .build();
    }

    /**
     * 获取Retrofit
     * @param client
     * @return
     */
    public Retrofit getRetrofit(OkHttpClient client){
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);
        return retrofit.build();
    }

    /**
     * Returns the VM's default SSL socket factory, using {@code trustManager} for trusted root
     * certificates.
     */
    private SSLSocketFactory defaultSslSocketFactory(X509TrustManager trustManager)
            throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[] { trustManager }, null);

        return sslContext.getSocketFactory();
    }

    /** Returns a trust manager that trusts the VM's default certificate authorities. */
    private X509TrustManager defaultTrustManager() throws GeneralSecurityException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

}
