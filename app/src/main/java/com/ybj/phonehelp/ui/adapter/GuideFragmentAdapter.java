package com.ybj.phonehelp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class GuideFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;

    public void setFragments(List<Fragment> fragments) {
        if(fragments == null) {
            mFragments = new ArrayList<>();
        }else {
            mFragments = fragments;
        }
    }

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
