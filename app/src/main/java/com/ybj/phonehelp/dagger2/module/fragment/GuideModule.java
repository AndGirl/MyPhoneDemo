package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.presenter.GuideFragmentImpl;
import com.ybj.phonehelp.ui.fragment.GuideFragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/4.
 * GuideFragment中初始化操作
 */
@Module
public class GuideModule {

    @Provides
    public GuideFragmentImpl provideGuideFragmentImpl(){
        return new GuideFragmentImpl();
    }

    @Singleton
    @Provides
    public GuideFragment provideGuideFragment(){
        return new GuideFragment();
    }


}
