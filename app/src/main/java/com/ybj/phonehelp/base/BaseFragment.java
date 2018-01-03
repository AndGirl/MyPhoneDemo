package com.ybj.phonehelp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ybj.phonehelp.common.AppApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 杨阳洋 on 2018/1/3.
 */

public abstract class BaseFragment extends Fragment {

    private View mView;
    private Unbinder mBind;
    private AppApplication mApplication;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(setLayout(), container, false);
        mBind = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.mApplication = (AppApplication) getActivity().getApplication();
        setupAcitivtyComponent(mApplication.getAppComponent());

        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBind != null) {
            mBind.unbind();
        }
    }

    public abstract void setupAcitivtyComponent(AppComponent appComponent);

    public abstract int setLayout();

    public abstract void init();
}
