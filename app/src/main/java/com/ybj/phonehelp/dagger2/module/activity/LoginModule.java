package com.ybj.phonehelp.dagger2.module.activity;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.LoginActivityImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */
@Module
public class LoginModule {

    @Provides
    public LoginActivityImpl provideLoginActivityImpl(ApiService apiService){
        return new LoginActivityImpl(apiService);
    }

}
