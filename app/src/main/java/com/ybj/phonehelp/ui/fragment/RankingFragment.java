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
import com.ybj.phonehelp.bean.RankingBean;
import com.ybj.phonehelp.dagger2.component.DaggerRankingComponent;
import com.ybj.phonehelp.dagger2.module.fragment.RankingModule;
import com.ybj.phonehelp.presenter.RankingFragmentImpl;
import com.ybj.phonehelp.presenter.contract.RankingContract;
import com.ybj.phonehelp.ui.adapter.RankingAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 排行页面
 */
public class RankingFragment extends BaseProgressFragment implements RankingContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Inject
    RankingFragmentImpl mRankingFragmentImpl;
    private RankingAdapter mRankingAdapter;
    private int page = 0;

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRankingComponent.builder()
                .appComponent(appComponent)
                .rankingModule(new RankingModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_ranking;
    }

    @Override
    public void init() {
        mRankingFragmentImpl.attachView(this);
        mRankingFragmentImpl.requestDatas(page,true);
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
    public void showRecyclerView(RankingBean rankingBean) {
        mRankingAdapter.addData(rankingBean.getDatas());
        if (rankingBean.isHasMore()) {
            page++;
        }
        mRankingAdapter.setEnableLoadMore(rankingBean.isHasMore());
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
        mRankingAdapter.loadMoreComplete();
    }

    /**
     * 初始化RecyclerView的配置
     */
    private void initRecyclerView(){
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义),在这里会导致过渡重绘
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRankingAdapter = new RankingAdapter(R.layout.template_appinfo);
        mRankingAdapter.setShowBrief(false);
        mRankingAdapter.setShowCategoryName(true);
        mRankingAdapter.setShowPosition(true);
        //加载更多
        mRankingAdapter.setOnLoadMoreListener(this);
        mRecycleView.setAdapter(mRankingAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        mRankingFragmentImpl.requestDatas(page,false);
    }
}
