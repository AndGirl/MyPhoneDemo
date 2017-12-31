package com.ybj.phonehelp.base;

import com.ybj.phonehelp.common.config.AppModule;
import com.ybj.phonehelp.dagger2.module.HttpModule;
import com.ybj.phonehelp.http.ApiService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    public ApiService getApiService();


}
