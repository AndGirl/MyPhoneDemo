package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.CategoryAppFragmentImpl;
import com.ybj.phonehelp.presenter.GameFragmentImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/23.
 */
@Module
public class CategoryAppModule {

    @Provides
    public CategoryAppFragmentImpl provideCategoryAppFragmentImpl(ApiService apiService){
        return new CategoryAppFragmentImpl(apiService);
    }

}
