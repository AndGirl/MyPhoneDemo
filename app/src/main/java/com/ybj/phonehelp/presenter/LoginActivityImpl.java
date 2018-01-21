package com.ybj.phonehelp.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.ybj.phonehelp.bean.LoginBean;
import com.ybj.phonehelp.bean.LoginRequestBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.exception.ApiException;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.common.util.ACache;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.LoginContract;
import com.ybj.phonehelp.ui.activity.LoginActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */

public class LoginActivityImpl implements LoginContract.Presenter {

    private ApiService mApiService;
    private LoginContract.View view;

    public LoginActivityImpl(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void attachView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas(String email, String password) {
        LoginRequestBean params = new LoginRequestBean();
        params.setEmail(email);
        params.setPassword(password);
        mApiService.login(params)
                .compose(RxHttpResponseCompat.<LoginBean>compatResult())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        Log.e("LoginActivity", new Gson().toJson(loginBean));
                        view.onSuccessMsg("登录成功");
                        saveUser(loginBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.dimissLoading();
                        view.onDefaultMsg(((ApiException) throwable).getDisplayMessage());
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

    /**
     * 保存用户数据
     * @param loginBean
     */
    private void saveUser(LoginBean loginBean) {
        ACache aCache = ACache.get(((LoginActivity)view).getBaseContext());

        aCache.put(Constant.TOKEN,loginBean.getToken());
        aCache.put(Constant.USER,new Gson().toJson(loginBean.getUser()));
    }

}
