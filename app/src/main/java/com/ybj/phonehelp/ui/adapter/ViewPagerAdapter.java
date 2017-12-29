package com.ybj.phonehelp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.ybj.phonehelp.ui.bean.FragmentInfo;
import com.ybj.phonehelp.ui.fragment.GamingFragment;
import com.ybj.phonehelp.ui.fragment.CategoryFragment;
import com.ybj.phonehelp.ui.fragment.RankingFragment;
import com.ybj.phonehelp.ui.fragment.RecommendFragment;

import java.util.ArrayList;

/**
 * Created by 杨阳洋 on 2017/12/17.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<FragmentInfo> fragments = new ArrayList<FragmentInfo>(4);

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        fragments.add(new FragmentInfo("推荐", RecommendFragment.class));
        fragments.add(new FragmentInfo("排行", RankingFragment.class));
        fragments.add(new FragmentInfo("游戏", GamingFragment.class));
        fragments.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) fragments.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
