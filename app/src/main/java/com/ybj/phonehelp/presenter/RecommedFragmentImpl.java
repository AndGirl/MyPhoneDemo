package com.ybj.phonehelp.presenter;

import android.util.Log;

import com.ybj.phonehelp.bean.AppInfo;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.RecommendContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 处理RecommedFragment的业务逻辑
 */

public class RecommedFragmentImpl implements RecommendContract.Presenter{

    private List<AppInfo.DatasBean> mDatasBeen;
    private RecommendContract.View view;

    private ApiService mApiService;

    public RecommedFragmentImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void attachView(RecommendContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas() {

        view.showLodading();

//        OkHttpManager manager = new OkHttpManager();
//
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
//                .create(ApiService.class);

        mApiService.getApps("{'page':0}").enqueue(new Callback<AppInfo>() {
            @Override
            public void onResponse(Call<AppInfo> call, Response<AppInfo> response) {
                Log.e("TAG", "成功");
                if("success".equals(response.body().getMessage())) {
                    mDatasBeen = response.body().getDatas();
                    if(mDatasBeen != null && mDatasBeen.size() > 0) {
                        view.showRecyclerView(mDatasBeen);
                    }
                }else{
                    view.showNoData();
                }
                view.dimissLoading();
            }

            @Override
            public void onFailure(Call<AppInfo> call, Throwable t) {
                view.dimissLoading();
                view.showErrorMessage(t.getMessage());
            }
        });
    }
}
