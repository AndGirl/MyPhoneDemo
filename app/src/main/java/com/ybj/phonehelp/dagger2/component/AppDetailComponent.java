package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.activity.AppDetailModule;
import com.ybj.phonehelp.ui.fragment.AppDetailFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/2/25.
 */
@FragmentScope
@Component(modules = AppDetailModule.class,dependencies = AppComponent.class)
public interface AppDetailComponent {

    void inject(AppDetailFragment fragment);

}
