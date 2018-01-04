package com.ybj.phonehelp.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

import com.mikepenz.iconics.context.IconicsLayoutInflater;
import com.ybj.phonehelp.common.AppApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 杨阳洋 on 2018/1/3.
 * 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Unbinder mBind;
    private AppApplication mApplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        setContentView(setLayout());

        mBind = ButterKnife.bind(this);

        //Dagger2
        mApplication = (AppApplication) getApplication();
        setupActivityComponent(mApplication.getAppComponent());

        init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBind != null) {
            mBind.unbind();
        }
    }

    public abstract int setLayout();

    public abstract void setupActivityComponent(AppComponent appComponent);

    public abstract void init();

    protected void startActivity(Class c){
        this.startActivity(new Intent(this,c));
    }

}
