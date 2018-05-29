package com.ybj.phonehelp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.ybj.phonehelp.bean.AppDetailsBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.AppDetailContract;
import com.ybj.phonehelp.ui.fragment.AppDetailFragment;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/2/25.
 */

public class AppDetailActivityImpl implements AppDetailContract.Presenter {

    private ApiService mApiService;
    private AppDetailContract.View view;

    public AppDetailActivityImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void attachView(AppDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas(int id) {
        mApiService.getAppDetailsById(id)
                .compose(RxHttpResponseCompat.<AppDetailsBean>compatResult(((AppDetailFragment)view).getActivity()))
                .subscribe(new Consumer<AppDetailsBean>() {
                    @Override
                    public void accept(AppDetailsBean appDetailsBean) throws Exception {
                        Log.e("LoginActivity", new Gson().toJson(appDetailsBean));
                        view.showAppDetail(appDetailsBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dimissLoading();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        view.dimissLoading();
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLodading();
                    }
                });
    }
}
