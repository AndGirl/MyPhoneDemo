package com.ybj.phonehelp.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseProgressFragment;
import com.ybj.phonehelp.bean.GameBean;
import com.ybj.phonehelp.dagger2.component.DaggerGameComponent;
import com.ybj.phonehelp.dagger2.module.fragment.GameModule;
import com.ybj.phonehelp.presenter.GameFragmentImpl;
import com.ybj.phonehelp.presenter.contract.GameContract;
import com.ybj.phonehelp.ui.activity.AppDetailActivity;
import com.ybj.phonehelp.ui.adapter.GameFragmentAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * 游戏排行页面
 */
public class GamingFragment extends BaseProgressFragment implements GameContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Inject
    GameFragmentImpl mGameFragmentImpl;

    private GameFragmentAdapter mGameFragmentAdapter;

    private int page = 0;

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerGameComponent.builder()
                .appComponent(appComponent)
                .gameModule(new GameModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_gaming;
    }

    @Override
    public void init() {
        mGameFragmentImpl.attachView(this);
        mGameFragmentImpl.requestDatas(page,true);
        initRecyclerView();
        mRecycleView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                //验证列表突出显示效果
                mApplication.setView(view);
                startActivity(new Intent(getActivity(), AppDetailActivity.class));
            }
        });
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
    private void initRecyclerView(){
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义),在这里会导致过渡重绘
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mGameFragmentAdapter = new GameFragmentAdapter(R.layout.template_appinfo);
        mGameFragmentAdapter.setShowBrief(false);
        mGameFragmentAdapter.setShowCategoryName(true);
        mGameFragmentAdapter.setShowPosition(true);
        //加载更多
        mGameFragmentAdapter.setOnLoadMoreListener(this);
        mRecycleView.setAdapter(mGameFragmentAdapter);
    }

    @Override
    public void onLoadMoreRequested() {
        mGameFragmentImpl.requestDatas(page,false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mGameFragmentImpl.detachView();
    }
}
