package com.ybj.phonehelp.presenter;

import android.view.View;

import com.ybj.phonehelp.bean.GameBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.CategoryAppContract;
import com.ybj.phonehelp.ui.fragment.CategoryAppFragment;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/1/23.
 */

public class CategoryAppFragmentImpl implements CategoryAppContract.Presenter {

    private ApiService mApiService;
    private CategoryAppContract.View view;
    private String mMessage;

    public CategoryAppFragmentImpl(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void attachView(CategoryAppContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void requestDatas(final int categoryId, int page, final int flagType, final boolean isLoading) {
        
        if(flagType == 0) {//精品
            mApiService.getFeaturedAppsByCategory(categoryId,page)
                    .compose(RxHttpResponseCompat.<GameBean>compatResult(((CategoryAppFragment)view).getActivity()))
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
                                        requestDatas(categoryId,0,flagType,true);
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
                                    requestDatas(categoryId,0,flagType,true);
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
                                        requestDatas(categoryId,0,flagType,true);
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
        }else if(flagType == 1) {//排行
            mApiService.getTopListAppsByCategory(categoryId,page)
                    .compose(RxHttpResponseCompat.<GameBean>compatResult(((CategoryAppFragment)view).getActivity()))
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
                                        requestDatas(categoryId,0,flagType,true);
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
                                    requestDatas(categoryId,0,flagType,true);
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
                                        requestDatas(categoryId,0,flagType,true);
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
        }else if(flagType == 2) {//新品
            mApiService.getNewListAppsByCategory(categoryId,page)
                    .compose(RxHttpResponseCompat.<GameBean>compatResult(((CategoryAppFragment)view).getActivity()))
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
                                        requestDatas(categoryId,0,flagType,true);
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
                                    requestDatas(categoryId,0,flagType,true);
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
                                        requestDatas(categoryId,0,flagType,true);
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
}
