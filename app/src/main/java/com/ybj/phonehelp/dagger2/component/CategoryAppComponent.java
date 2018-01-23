package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.fragment.CategoryAppModule;
import com.ybj.phonehelp.ui.fragment.CategoryAppFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/23.
 */
@FragmentScope
@Component(modules = CategoryAppModule.class,dependencies = AppComponent.class)
public interface CategoryAppComponent {

    void inject(CategoryAppFragment categoryAppFragment);

}
