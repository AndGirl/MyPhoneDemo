package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.fragment.CategoryModule;
import com.ybj.phonehelp.ui.fragment.CategoryFragment;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */
@FragmentScope
@Component(modules = CategoryModule.class,dependencies = AppComponent.class)
public interface CategoryComponent {

    void inject(CategoryFragment categoryFragment);

}
