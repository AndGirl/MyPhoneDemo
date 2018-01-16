package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.fragment.GameModule;
import com.ybj.phonehelp.ui.fragment.GamingFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/16.
 */
@FragmentScope
@Component(modules = GameModule.class,dependencies = AppComponent.class)
public interface GameComponent {
    void inject(GamingFragment gamingFragment);
}
