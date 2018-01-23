package com.ybj.phonehelp.ui.activity;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.bean.CategoryBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.ui.adapter.CategoryAppViewPagerAdapter;

import java.lang.reflect.Field;

import butterknife.BindView;

/**
 * 分类详情展示页面
 */

public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.activity_cateogry_app)
    LinearLayout mActivityCateogryApp;
    private CategoryBean mCategoryBean;

    @Override
    public int setLayout() {
        return R.layout.activity_category_app;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initData();
    }

    /**
     * 添加ViewPager的数据
     */
    private void initData() {
        //获取上个页面的数据
        mCategoryBean = (CategoryBean) getIntent().getSerializableExtra(Constant.CATEGORY);
        initTabLayout();

        CategoryAppViewPagerAdapter viewPagerAdapter =
                new CategoryAppViewPagerAdapter(getSupportFragmentManager(),mCategoryBean.getId());
        mViewPager.setCurrentItem(0);
        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        setIndicator(mTabLayout, 20, 20);
    }

    /**
     * 固定下划线宽度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    /**
     * 设置带返回的toolbar
     */
    private void initTabLayout() {
        mToolBar.setTitle(mCategoryBean.getName());

        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(GoogleMaterial.Icon.gmd_keyboard_backspace)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)));
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
