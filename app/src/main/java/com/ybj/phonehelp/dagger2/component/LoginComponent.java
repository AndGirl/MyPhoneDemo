package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.FragmentScope;
import com.ybj.phonehelp.dagger2.module.activity.LoginModule;
import com.ybj.phonehelp.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */
@FragmentScope
@Component(modules = LoginModule.class,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
