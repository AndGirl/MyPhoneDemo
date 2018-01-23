package com.ybj.phonehelp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ybj.phonehelp.ui.fragment.CategoryAppFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨阳洋 on 2018/1/23.
 * 分类详情页面
 */

public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> mFragments = new ArrayList<>(3);
    private List<String> titles = new ArrayList<>(3);
    private int mCategoryId;

    public CategoryAppViewPagerAdapter(FragmentManager fm, int id) {
        super(fm);

        this.mCategoryId = id;

        mFragments.add(new CategoryAppFragment(mCategoryId,0));
        mFragments.add(new CategoryAppFragment(mCategoryId,1));
        mFragments.add(new CategoryAppFragment(mCategoryId,2));

        titles.add("精品");
        titles.add("排行");
        titles.add("新品");
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
