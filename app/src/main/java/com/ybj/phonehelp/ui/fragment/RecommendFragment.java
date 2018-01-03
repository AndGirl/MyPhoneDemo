package com.ybj.phonehelp.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseFragment;
import com.ybj.phonehelp.bean.AppInfo;
import com.ybj.phonehelp.dagger2.component.DaggerRecommendComponent;
import com.ybj.phonehelp.dagger2.module.fragment.RecommendModule;
import com.ybj.phonehelp.presenter.RecommedFragmentImpl;
import com.ybj.phonehelp.presenter.contract.RecommendContract;
import com.ybj.phonehelp.ui.adapter.RecommendAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment implements RecommendContract.View {


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    @Inject
    RecommedFragmentImpl mRecommedFragmentImpl;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void init() {
        mRecommedFragmentImpl.attachView(this);
        mRecommedFragmentImpl.requestDatas();
    }

    @Override
    public void showLodading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void dimissLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(getActivity(), "服务器开小差了：" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRecyclerView(List<AppInfo.DatasBean> datasBeen) {
        initRecyclerView(datasBeen);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂时无数据，请吃完饭再来", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecommedFragmentImpl.detachView();
    }

    private void initRecyclerView(List<AppInfo.DatasBean> datasBeen) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyclerView设置分割线(这个可以对DividerItemDecoration进行修改，自定义)
        mRecycleView.addItemDecoration(new DividerItemDecoration(getActivity()
                , DividerItemDecoration.VERTICAL_LIST));
        //动画
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
        mRecycleView.setAdapter(new RecommendAdapter(getActivity(),datasBeen));
    }

}
