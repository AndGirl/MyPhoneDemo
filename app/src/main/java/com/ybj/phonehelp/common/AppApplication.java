package com.ybj.phonehelp.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;

import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.DaggerAppComponent;
import com.ybj.phonehelp.common.config.AppModule;
import com.ybj.phonehelp.common.util.MyService;
import com.ybj.phonehelp.common.util.ScreenListener;
import com.ybj.phonehelp.dagger2.module.HttpModule;
import com.ybj.phonehelp.ui.activity.MainActivity;

/**
 * Created by 杨阳洋 on 2018/1/1.
 */

public class AppApplication extends Application {

    //判断当前app数量
    private int appCount = 0;


    public Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1 :
                    Log.e("Uart", "结束MainActivity");
                    MainActivity.mMainActivity.finish();
                    break;
            }
        }
    };

    //锁屏状态的监听
    public ScreenListener mScreenListener;

    private AppApplication mAppApplication;

    private AppComponent mAppComponent;

    private View mView;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Uart", "Application创建了");
        
        mAppApplication = this;

        final Intent intent = new Intent(mAppApplication, MyService.class);

        mScreenListener = new ScreenListener(mAppApplication);
        mScreenListener.register(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.e("Uart", "MainActivity --> 亮屏--> ");
                Log.e("Uart", "MainActivity --> 亮屏开启服务--> ");
            }

            @Override
            public void onScreenOff() {
                Log.e("Uart", "MainActivity --> 息屏--> ");
                Log.e("Uart", "MainActivity --> 息屏停止服务--> ");
                handler.removeCallbacksAndMessages(null);
                mAppApplication.stopService(intent);
            }

            @Override
            public void onUserPresent() {
                Log.e("Uart", "MainActivity --> 屏幕解锁--> ");
                Log.e("Uart", "MainActivity --> 解锁屏幕开启服务--> ");
                mAppApplication.startService(intent);
            }
        });

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if(activity instanceof MainActivity) {
                    mAppApplication.startService(intent);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                appCount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                appCount--;
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                if(activity instanceof MainActivity) {

                    if (activity.isDestroyed() && mScreenListener != null) {
                        Log.e("Uart", "mScreenListener 消亡了哈哈哈哈");
                        //移出所有消息
                        handler.removeCallbacksAndMessages(null);
                        mScreenListener.unregister();
                        mAppApplication.stopService(intent);
                    }
                }
            }
        });


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

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public int getAppCount() {
        return appCount;
    }

}
