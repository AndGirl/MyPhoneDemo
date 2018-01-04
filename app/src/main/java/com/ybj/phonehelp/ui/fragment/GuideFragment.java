package com.ybj.phonehelp.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseFragment;
import com.ybj.phonehelp.dagger2.component.DaggerGuideComponent;
import com.ybj.phonehelp.dagger2.module.fragment.GuideModule;
import com.ybj.phonehelp.presenter.GuideFragmentImpl;
import com.ybj.phonehelp.presenter.contract.GuideContract;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class GuideFragment extends BaseFragment implements GuideContract.View{

    public static final String IMG_ID = "IMG_ID";
    public static final String COLOR_ID = "COLOR_ID";
    public static final String TEXT_ID = "TEXT_ID";
    @BindView(R.id.imgView)
    ImageView mImgView;
    @BindView(R.id.text)
    TextView mText;
    @BindView(R.id.rootView)
    LinearLayout mRootView;
    @Inject
    GuideFragmentImpl mGuideFragment;

    public static GuideFragment newInstance(int imgResId, int bgColorResId, int textResId){

        GuideFragment guideFragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMG_ID,imgResId);
        bundle.putInt(COLOR_ID,bgColorResId);
        bundle.putInt(TEXT_ID,textResId);
        //activity向fragment传参
        guideFragment.setArguments(bundle);
        return guideFragment;

    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {
        DaggerGuideComponent.builder()
                .guideModule(new GuideModule())
                .build().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    public void init() {
        mGuideFragment.attachView(this);
        mGuideFragment.setBtnDatas();
    }


    @Override
    public void showLodading() {

    }

    @Override
    public void dimissLoading() {

    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void showRecyclerView(Object o) {

    }

    @Override
    public void showFragment() {
        Bundle arguments = getArguments();
        int colorInt = arguments.getInt(COLOR_ID);
        int imgId = arguments.getInt(IMG_ID);
        int textId = arguments.getInt(TEXT_ID);

        mImgView.setImageResource(imgId);
        mRootView.setBackgroundResource(colorInt);
        mText.setText(textId);
    }
}
