package com.ybj.phonehelp.dagger2.module.fragment;

import android.content.Context;

import com.ybj.phonehelp.bean.AppInfo;
import com.ybj.phonehelp.presenter.RecommedFragmentImpl;
import com.ybj.phonehelp.ui.adapter.RecommendAdapter;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/1.
 * RecommendFragment中初始化操作
 */

@Module
public class RecommendModule {

    private Context mContext;
    private List<AppInfo.DatasBean> datasBeen;

    public RecommendModule(Context context, List<AppInfo.DatasBean> datasBeen) {
        mContext = context;
        this.datasBeen = datasBeen;
    }

    @Provides
    public RecommedFragmentImpl provideRecommedFragmentImpl(){
        return new RecommedFragmentImpl();
    }

    @Provides
    public RecommendAdapter provideRecommendAdapter(){
        return new RecommendAdapter(mContext,datasBeen);
    }

}
