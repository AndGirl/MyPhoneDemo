package com.ybj.phonehelp.dagger2.module.activity;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.AppDetailActivityImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/2/25.
 */
@Module
public class AppDetailModule {

    @Provides
    public AppDetailActivityImpl provideAppDetailActivityImpl(ApiService apiService){
        return new AppDetailActivityImpl(apiService);
    }

}
