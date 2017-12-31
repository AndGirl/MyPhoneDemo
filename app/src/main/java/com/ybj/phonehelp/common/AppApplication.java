package com.ybj.phonehelp.common;

import android.app.Application;
import android.content.Context;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.DaggerAppComponent;
import com.ybj.phonehelp.common.config.AppModule;
import com.ybj.phonehelp.dagger2.module.HttpModule;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */

public class AppApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .httpModule(new HttpModule())
                .build();

    }

    public static AppApplication get(Context context){
        return (AppApplication) context.getApplicationContext();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
