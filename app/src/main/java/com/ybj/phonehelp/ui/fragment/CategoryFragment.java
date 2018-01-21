package com.ybj.phonehelp.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseProgressFragment;
import com.ybj.phonehelp.bean.CategoryBean;
import com.ybj.phonehelp.dagger2.component.DaggerCategoryComponent;
import com.ybj.phonehelp.dagger2.module.fragment.CategoryModule;
import com.ybj.phonehelp.presenter.CategoryFragmentImpl;
import com.ybj.phonehelp.presenter.contract.CategoryContract;
import com.ybj.phonehelp.ui.adapter.CategoryAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseProgressFragment implements CategoryContract.View {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    @Inject
    CategoryFragmentImpl mCategoryFragment;

    private CategoryAdapter mCategoryAdapter;

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder()
                .appComponent(appComponent)
                .categoryModule(new CategoryModule())
                .build().inject(this);

    }

    @Override
    public int setLayout() {
        return R.layout.fragment_category;
    }

    @Override
    public void init() {
        mCategoryFragment.attachView(this);
        mCategoryFragment.requestDatas();
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
    public void showRecyclerView(List<CategoryBean> categories) {
        mCategoryAdapter.addData(categories);
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
    public void onDestroy() {
        super.onDestroy();
        mCategoryFragment.detachView();
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
        mCategoryAdapter = new CategoryAdapter(R.layout.template_category);
        mCategoryAdapter.setShowBrief(false);
        mCategoryAdapter.setShowCategoryName(true);
        mCategoryAdapter.setShowPosition(true);
        mRecycleView.setAdapter(mCategoryAdapter);
    }

}
