package com.ybj.phonehelp.dagger2.module.fragment;

import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.RankingFragmentImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */
@Module
public class RankingModule {

    @Provides
    public RankingFragmentImpl provideRankingFragmentImpl(ApiService apiService){
        return new RankingFragmentImpl(apiService);
    }

}
