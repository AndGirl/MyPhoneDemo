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
 * https://github.com/xmagicj/LazyFragment/blob/master/app/src/main/java/com/xmagicj/android/lazyfragment/MainActivity.java(预加载问题处理方案)
 * https://www.jianshu.com/p/b871ba69850f
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

    /**
     * 是否可见状态 为了避免和{@link Fragment#isVisible()}冲突 换个名字
     */
    private boolean isFragmentVisible;
    /**
     * 标志位，View已经初始化完成。
     * 2016/04/29
     * 用isAdded()属性代替
     * 2016/05/03
     * isPrepared还是准一些,isAdded有可能出现onCreateView没走完但是isAdded了
     */
    private boolean isPrepared;
    /**
     * 是否第一次加载
     */
    private boolean isFirstLoad = true;
    /**
     * <pre>
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     * 一般用于PagerAdapter需要刷新各个子Fragment的场景
     * 不要new 新的 PagerAdapter 而采取reset数据的方式
     * 所以要求Fragment重新走initData方法
     * 故使用 {@link BaseFragment#(boolean)}来让Fragment下次执行initData
     * </pre>
     */
    private boolean forceLoad = false;

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

        isFirstLoad = true;
        isPrepared = true;
        lazyLoad();
        //
        // init();
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
        isPrepared = false;
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

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     * visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected void onVisible() {
        isFragmentVisible = true;
        lazyLoad();
    }

    protected void onInvisible() {
        isFragmentVisible = false;
    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    protected void lazyLoad() {
        if (isPrepared() && isFragmentVisible()) {
            if (forceLoad || isFirstLoad()) {
                forceLoad = false;
                isFirstLoad = false;
                init();
            }
        }
    }

    public boolean isPrepared() {
        return isPrepared;
    }
    /**
     * 忽略isFirstLoad的值，强制刷新数据，但仍要Visible & Prepared
     */
    public void setForceLoad(boolean forceLoad) {
        this.forceLoad = forceLoad;
    }

    public boolean isFirstLoad() {
        return isFirstLoad;
    }

    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

}
