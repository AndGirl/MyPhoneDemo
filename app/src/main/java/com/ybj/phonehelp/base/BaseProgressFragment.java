package com.ybj.phonehelp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ybj.phonehelp.common.AppApplication;
import com.ybj.phonehelp.common.config.VaryViewManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 杨阳洋 on 2018/1/8.
 * 抽象baseFragment 实现fragment的懒加载
 */

public abstract class BaseProgressFragment extends Fragment {

    /**
     * ui 模式
     */
    public static final int NORMAL_TYPE = 0;
    public static final int ERROR_NET_TYPE = 1;
    public static final int EMPTY_TYPE = 2;
    public static final int LOADING_TYPE = 3;
    protected int mCurrentViewType = NORMAL_TYPE;
    protected VaryViewManager mVaryViewManager = null;

    private Unbinder mBind;
    private AppApplication mApplication;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(setLayout(),null);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mBind = ButterKnife.bind(this, mView);
        if (null != getLoadingTargetView()) {//设置动态view加载帮助类
            mVaryViewManager = new VaryViewManager(getLoadingTargetView());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }


    public abstract void setupAcitivtyComponent(AppComponent appComponent);

    public abstract int setLayout();

    public abstract void init();

    /**
     * 让某个界面显示加载加载进图
     *
     * @return
     */
    protected abstract View getLoadingTargetView();

    /**
     * 显示正在加载框
     */
    protected void toggleShowLoading(boolean toggle) {
        if (null == mVaryViewManager) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            mCurrentViewType = LOADING_TYPE;
            mVaryViewManager.showLoading();
        } else {
            mCurrentViewType = NORMAL_TYPE;
            mVaryViewManager.restore();
        }
    }

    /**
     * 显示没有数据
     */
    protected void toggleShowEmpty(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewManager) {
            throw new IllegalArgumentException("You must return a right target view for empty");
        }
        if (toggle) {
            mCurrentViewType = EMPTY_TYPE;
            mVaryViewManager.showEmpty(onClickListener);
        } else {
            mCurrentViewType = NORMAL_TYPE;
            mVaryViewManager.restore();
        }
    }


    /**
     * 显示网络错误
     */
    protected void toggleShowNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewManager) {
            throw new IllegalArgumentException("You must return a right target view for net error");
        }
        if (toggle) {
            mCurrentViewType = ERROR_NET_TYPE;
            mVaryViewManager.showNetworkError(onClickListener);
        } else {
            mCurrentViewType = NORMAL_TYPE;
            mVaryViewManager.restore();
        }
    }

}
