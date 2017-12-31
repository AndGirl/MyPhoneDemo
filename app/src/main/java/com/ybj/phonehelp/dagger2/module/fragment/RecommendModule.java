package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.RecommedFragmentImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/1.
 * RecommendFragment中初始化操作
 */

@Module
public class RecommendModule {

    @Provides
    public RecommedFragmentImpl provideRecommedFragmentImpl(ApiService apiService){
        return new RecommedFragmentImpl(apiService);
    }

}
