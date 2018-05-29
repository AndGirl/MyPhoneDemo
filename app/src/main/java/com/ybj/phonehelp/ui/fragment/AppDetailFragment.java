package com.ybj.phonehelp.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseFragment;
import com.ybj.phonehelp.bean.AppDetailsBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.DateUtils;
import com.ybj.phonehelp.dagger2.component.DaggerAppDetailComponent;
import com.ybj.phonehelp.dagger2.module.activity.AppDetailModule;
import com.ybj.phonehelp.imageloader.ImageLoader;
import com.ybj.phonehelp.presenter.AppDetailActivityImpl;
import com.ybj.phonehelp.presenter.contract.AppDetailContract;
import com.ybj.phonehelp.ui.adapter.AppDetailAdapter;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 杨阳洋 on 2018/2/28.
 */

public class AppDetailFragment extends BaseFragment implements AppDetailContract.View {

    @BindView(R.id.view_gallery)
    LinearLayout mViewGallery;
    @BindView(R.id.expandable_text)
    TextView mExpandableText;
    @BindView(R.id.expand_collapse)
    ImageButton mExpandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView mViewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView mTxtUpdateTime;
    @BindView(R.id.txt_version)
    TextView mTxtVersion;
    @BindView(R.id.txt_apk_size)
    TextView mTxtApkSize;
    @BindView(R.id.txt_publisher)
    TextView mTxtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView mTxtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView mRecyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView mRecyclerViewRelate;

    @Inject
    AppDetailActivityImpl mAppDetailActivityImpl;
    private LayoutInflater mInflater;
    private AppDetailAdapter mAppDetailAdapter;
    private int id;

    public static AppDetailFragment newInstance(int id) {
        AppDetailFragment appDetailFragment = new AppDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        appDetailFragment.setArguments(bundle);
        return appDetailFragment;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder()
                .appComponent(appComponent)
                .appDetailModule(new AppDetailModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_detail_app;
    }

    @Override
    public void init() {
        mAppDetailActivityImpl.attachView(this);
        Bundle arguments = getArguments();
        int id = arguments.getInt("id");
        mInflater = LayoutInflater.from(getActivity());

        mAppDetailActivityImpl.requestDatas(id);
    }

    @Override
    public void showLodading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void showRecyclerView(AppDetailsBean appDetailsBean) {

    }

    @Override
    public void showEmpty(View.OnClickListener listener) {

    }

    @Override
    public void restoreView() {

    }

    @Override
    public void showNetError(View.OnClickListener listener) {

    }

    @Override
    public void showAppDetail(AppDetailsBean appDetailsBean) {
        showScreenshot(appDetailsBean.getScreenshot());

        mViewIntroduction.setText(appDetailsBean.getIntroduction());

        mTxtUpdateTime.setText(DateUtils.formatDate(appDetailsBean.getUpdateTime()));
        mTxtApkSize.setText((appDetailsBean.getApkSize() / 1014 / 1024) + " Mb");
        mTxtVersion.setText(appDetailsBean.getVersionName());
        mTxtPublisher.setText(appDetailsBean.getPublisherName());
        mTxtPublisher2.setText(appDetailsBean.getPublisherName());

        mAppDetailAdapter = new AppDetailAdapter(R.layout.template_appinfo2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerViewSameDev.setLayoutManager(layoutManager);

        mAppDetailAdapter.addData(appDetailsBean.getSameDevAppDetailsBeanList());
        mRecyclerViewSameDev.setAdapter(mAppDetailAdapter);

        mAppDetailAdapter = new AppDetailAdapter(R.layout.template_appinfo2);

        mRecyclerViewRelate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        mAppDetailAdapter.addData(appDetailsBean.getRelateAppDetailsBeanList());
        mRecyclerViewRelate.setAdapter(mAppDetailAdapter);

    }

    private void showScreenshot(String screenShot){
        List<String> urls = Arrays.asList(screenShot.split(","));
        for (String url:urls){
            ImageView imageView = (ImageView) mInflater.inflate(R.layout.template_imageview, mViewGallery, false);
            ImageLoader.load(Constant.BASE_IMAGEURL + url, imageView);
            mViewGallery.addView(imageView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
