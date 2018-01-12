package com.ybj.phonehelp.presenter;

import android.view.View;

import com.ybj.phonehelp.bean.RankingBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.RankingContract;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */

public class RankingFragmentImpl implements RankingContract.Presenter {

    private ApiService mApiService;
    private RankingContract.View view;
    private String mMessage;

    public RankingFragmentImpl(ApiService apiService) {
        mApiService = apiService;
    }

    @Override
    public void attachView(RankingContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas(int page) {
        mApiService.toplist(page)
                .compose(RxHttpResponseCompat.<RankingBean>compatResult())
                .subscribe(new Consumer<RankingBean>() {
                    @Override
                    public void accept(RankingBean rankingBean) throws Exception {
                        mMessage = rankingBean.getMessage();
                        int status = rankingBean.getStatus();
                        if(status == 1) {
                            List<RankingBean.DatasBean> datas =
                                    rankingBean.getDatas();
                            if(datas != null && datas.size() > 0) {
                                view.showRecyclerView(rankingBean);
                            }
                        }else{
                            view.showEmpty(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestDatas(0);
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
                                requestDatas(0);
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
                                    requestDatas(0);
                                }
                            });
                        }
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        view.showLodading();
                    }
                });
    }
}
