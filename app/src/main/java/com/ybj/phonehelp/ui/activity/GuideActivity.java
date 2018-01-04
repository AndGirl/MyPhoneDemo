package com.ybj.phonehelp.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.ACache;
import com.ybj.phonehelp.transformer.TabletTransformer;
import com.ybj.phonehelp.ui.adapter.GuideFragmentAdapter;
import com.ybj.phonehelp.ui.fragment.GuideFragment;
import com.ybj.phonehelp.widget.CircleIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页面只有跳转
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.btn_enter)
    Button mBtnEnter;
    @BindView(R.id.indicator)
    CircleIndicator mIndicator;
    @BindView(R.id.activity_guide)
    RelativeLayout mActivityGuide;
    private GuideFragmentAdapter mGuideFragmentAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initData();
    }

    private void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.drawable.guide_1, R.color.color_bg_guide1, R.string.guide_1));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_2, R.color.color_bg_guide2, R.string.guide_2));
        fragments.add(GuideFragment.newInstance(R.drawable.guide_3, R.color.color_bg_guide3, R.string.guide_3));

        mGuideFragmentAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mGuideFragmentAdapter.setFragments(fragments);

        mViewpager.setCurrentItem(0);
        mViewpager.setOffscreenPageLimit(mGuideFragmentAdapter.getCount());
        mViewpager.addOnPageChangeListener(this);
        mViewpager.setAdapter(mGuideFragmentAdapter);
        mViewpager.setPageTransformer(true,new TabletTransformer());
        mIndicator.setViewPager(mViewpager);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mBtnEnter.setVisibility((position == mGuideFragmentAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.btn_enter)
    public void onViewClicked() {
        ACache.get(this)
                .put(Constant.IS_SHOW_GUIDE,"0");
        startActivity(MainActivity.class);
        this.finish();
    }
}
