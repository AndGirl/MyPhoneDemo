package com.ybj.phonehelp.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseProgressFragment;
import com.ybj.phonehelp.bean.GameBean;
import com.ybj.phonehelp.dagger2.component.DaggerCategoryAppComponent;
import com.ybj.phonehelp.dagger2.module.fragment.CategoryAppModule;
import com.ybj.phonehelp.presenter.CategoryAppFragmentImpl;
import com.ybj.phonehelp.presenter.contract.CategoryAppContract;
import com.ybj.phonehelp.ui.adapter.GameFragmentAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 分类页面详情
 */
public class CategoryAppFragment extends BaseProgressFragment implements CategoryAppContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    private int categoryId;
    private int mFlagType;
    int page = 0;

    @Inject
    CategoryAppFragmentImpl mCategoryAppFragment;

    private GameFragmentAdapter mGameFragmentAdapter;

    public CategoryAppFragment(int categoryId, int flagType) {
        this.categoryId = categoryId;
        this.mFlagType = flagType;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCategoryAppComponent.builder()
                .appComponent(appComponent)
                .categoryAppModule(new CategoryAppModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_category_app;
    }

    @Override
    public void init() {
        mCategoryAppFragment.attachView(this);
        mCategoryAppFragment.requestDatas(categoryId,page,mFlagType,true);
        initRecyclerView();
    }

    @Override
    protected View getLoadingTargetView() {
        return mRecycleView;
    }

    @Override
    public void showLodading() {
        toggleShowLoading(true);
    }

    @Override
    public void dimissLoading() {
        toggleShowLoading(false);
    }

    @Override
    public void showRecyclerView(GameBean gameBean) {
        mGameFragmentAdapter.addData(gameBean.getDatas());
        if(gameBean.isHasMore()) {
            page ++;
        }
        mGameFragmentAdapter.setEnableLoadMore(gameBean.isHasMore());
    }

    @Override
    public void showEmpty(View.OnClickListener listener) {
        toggleShowEmpty(true, listener);
    }

    @Override
    public void restoreView() {
        switch (mCurrentViewType) {
            case BaseProgressFragment.ERROR_NET_TYPE:
                toggleShowNetworkError(false, null);
                break;
            case BaseProgressFragment.LOADING_TYPE:
                toggleShowLoading(false);
                break;
            case BaseProgressFragment.EMPTY_TYPE:
                toggleShowEmpty(false, null);
                break;
        }
    }

    @Override
    public void showNetError(View.OnClickListener listener) {
        toggleShowNetworkError(true, listener);
    }

    @Override
    public void onLoadMoreComplete() {
        mGameFragmentAdapter.loadMoreComplete();
    }

    /**
     * 初始化RecyclerView的配置
     */
    private void initRecyclerView() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义),在这里会导致过渡重绘
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mGameFragmentAdapter = new GameFragmentAdapter(R.layout.template_appinfo);
        mGameFragmentAdapter.setShowBrief(true);
        mGameFragmentAdapter.setShowCategoryName(false);
        mGameFragmentAdapter.setShowPosition(false);
        //加载更多
        mGameFragmentAdapter.setOnLoadMoreListener(this);
        mRecycleView.setAdapter(mGameFragmentAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        mCategoryAppFragment.requestDatas(categoryId,page,mFlagType,false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCategoryAppFragment.detachView();
    }

}
