package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.fragment.RecommendModule;
import com.ybj.phonehelp.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */
@FragmentScope
@Component(modules = RecommendModule.class,dependencies = AppComponent.class)
public interface RecommendComponent {

    void inject(RecommendFragment fragment);

}
