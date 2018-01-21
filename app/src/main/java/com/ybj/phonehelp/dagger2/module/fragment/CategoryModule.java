package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.CategoryFragmentImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/21.
 * CategoryFragment初始化操作
 */
@Module
public class CategoryModule {

    @Provides
    public CategoryFragmentImpl provideCategoryFragmentImpl(ApiService apiService){
        return new CategoryFragmentImpl(apiService);
    }

}
