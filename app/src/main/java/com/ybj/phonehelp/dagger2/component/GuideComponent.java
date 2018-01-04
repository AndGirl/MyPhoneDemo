package com.ybj.phonehelp.dagger2.component;

import com.ybj.phonehelp.dagger2.module.fragment.GuideModule;
import com.ybj.phonehelp.ui.fragment.GuideFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */
@Singleton
@Component(modules = GuideModule.class)
public interface GuideComponent {
    void inject(GuideFragment guideFragment);

    GuideFragment getInstance();

}
