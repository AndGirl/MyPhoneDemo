package com.ybj.phonehelp.presenter;

import android.view.View;

import com.ybj.phonehelp.bean.GameBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.GameContract;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/1/16.
 * GameFragment页面逻辑处理
 */

public class GameFragmentImpl implements GameContract.Presenter {

    private ApiService mApiService;
    private GameContract.View view;
    private String mMessage;

    public GameFragmentImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void attachView(GameContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas(int page, final boolean isLoading) {
        mApiService.game(page)
                .compose(RxHttpResponseCompat.<GameBean>compatResult())
                .subscribe(new Consumer<GameBean>() {
                    @Override
                    public void accept(GameBean gameBean) throws Exception {
                        mMessage = gameBean.getMessage();
                        int status = gameBean.getStatus();
                        if(status == 1) {
                            List<GameBean.DatasBean> datas = gameBean.getDatas();
                            if(datas != null && datas.size() > 0) {
                                view.showRecyclerView(gameBean);
                            }
                        }else{
                            view.showEmpty(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestDatas(0,true);
                                }
                            });
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showNetError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestDatas(0,true);
                            }
                        });
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if("success".equals(mMessage)) {
                            view.restoreView();
                            view.onLoadMoreComplete();
                        }else{
                            view.showEmpty(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestDatas(0,true);
                                }
                            });
                        }
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if(isLoading) {
                            view.showLodading();
                        }
                    }
                });

    }
}
