package com.ybj.phonehelp.dagger2.module;

import android.app.Application;

import com.google.gson.Gson;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.http.CommonParamsInterceptor;

import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */
@Module
public class HttpModule {

    /**
     * 获取OkClient并对其进行基本配置
     * @return
     */
    @Provides
    @Singleton
    public OkHttpClient getOkHttpClient(Application appApplication, Gson gson){

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
                .addInterceptor(new CommonParamsInterceptor(appApplication,gson))
                // 连接超时时间设置
                .connectTimeout(20, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(20, TimeUnit.SECONDS)
                .sslSocketFactory(mSslSocketFactory,trustManager)
                .build();
    }

    /**
     * 获取Retrofit
     * @param client
     * @return
     */
    @Provides
    @Singleton
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
    @Provides
    @Singleton
    public SSLSocketFactory defaultSslSocketFactory(X509TrustManager trustManager) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return sslContext.getSocketFactory();
    }

    /** Returns a trust manager that trusts the VM's default certificate authorities. */
    @Provides
    @Singleton
    public X509TrustManager defaultTrustManager(){
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                    TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init((KeyStore) null);
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                throw new IllegalStateException("Unexpected default trust managers:"
                        + Arrays.toString(trustManagers));
            }
            return (X509TrustManager) trustManagers[0];
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }

}
