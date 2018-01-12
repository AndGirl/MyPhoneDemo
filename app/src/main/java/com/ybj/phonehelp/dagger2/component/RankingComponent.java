package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.fragment.RankingModule;
import com.ybj.phonehelp.ui.fragment.RankingFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */
@FragmentScope
@Component(modules = RankingModule.class,dependencies = AppComponent.class)
public interface RankingComponent {

    void inject(RankingFragment rankingFragment);

}
