package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.GameFragmentImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/16.
 * GameFragment初始化操作
 */
@Module
public class GameModule {

    @Provides
    public GameFragmentImpl provideGameFragmentImpl(ApiService apiService){
        return new GameFragmentImpl(apiService);
    }

}
