package com.ybj.phonehelp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.bean.AppInfo;
import com.ybj.phonehelp.presenter.RecommedFragmentImpl;
import com.ybj.phonehelp.presenter.contract.RecommendContract;
import com.ybj.phonehelp.ui.adapter.RecommendAdapter;
import com.ybj.phonehelp.ui.decoration.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends Fragment implements RecommendContract.View {


    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    @BindView(R.id.progress)
    ProgressBar mProgress;

    private RecommedFragmentImpl mRecommedFragmentImpl;

    public RecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        unbinder = ButterKnife.bind(this, view);


        mRecommedFragmentImpl = new RecommedFragmentImpl();
        mRecommedFragmentImpl.attachView(this);
        mRecommedFragmentImpl.requestDatas();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
        mRecycleView.setAdapter(new RecommendAdapter(getActivity(), datasBeen));
    }

}
