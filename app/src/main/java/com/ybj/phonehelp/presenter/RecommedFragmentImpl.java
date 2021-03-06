package com.ybj.phonehelp.presenter;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ybj.phonehelp.bean.RecommendBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.RecommendContract;
import com.ybj.phonehelp.ui.fragment.RecommendFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 处理RecommedFragment的业务逻辑
 * 注意：一定要修改ApiService给定一个基类
 */

public class RecommedFragmentImpl implements RecommendContract.Presenter {

    private RecommendContract.View view;

    private ApiService mApiService;
    private RecommendBean mRecommendBean;

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

//        OkHttpManager manager = new OkHttpManager();
//
//        ApiService apiService = manager.getRetrofit(manager.getOkHttpClient())
//                .create(ApiService.class);

//        mApiService.getApps("{'page':0}").enqueue(new Callback<AppInfo>() {
//            @Override
//            public void onResponse(Call<AppInfo> call, Response<AppInfo> response) {
//                Log.e("TAG", "成功");
//                if ("success".equals(response.body().getMessage())) {
//                    mDatasBeen = response.body().getDatas();
//                    if (mDatasBeen != null && mDatasBeen.size() > 0) {
//                        view.showRecyclerView(mDatasBeen);
//                    }
//                } else {
//                    view.showNoData();
//                }
//                view.dimissLoading();
//            }
//
//            @Override
//            public void onFailure(Call<AppInfo> call, Throwable t) {
//                view.dimissLoading();
//                view.showErrorMessage(t.getMessage());
//            }
//        });

//        mApiService.getApps("{'page':0}")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<AppInfo>() {
//            @Override
//            public void accept(AppInfo appInfo) throws Exception {
//                Log.e("TAG", "new Consumer<AppInfo>()");
//                if("success".equals(appInfo.getMessage())) {
//                    mDatasBeen = appInfo.getDatas();
//                    if(mDatasBeen != null && mDatasBeen.size() > 0) {
//                        view.showRecyclerView(mDatasBeen);
//                    }
//                }else{
//                    view.showNoData();
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                Log.e("TAG", "出错");
//                view.showErrorMessage(throwable.getMessage());
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//                Log.e("TAG", "结束");
//                view.dimissLoading();
//            }
//        }, new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//                Log.e("TAG", "准备工作");
//                view.showLodading();
//            }
//        });

        RxPermissions rxPermissions = new RxPermissions(((Fragment) view).getActivity());
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Function<Boolean, ObservableSource<RecommendBean>>() {
                    @Override
                    public ObservableSource<RecommendBean> apply(@NonNull Boolean aBoolean) throws Exception {
                        if(aBoolean) {
                         return mApiService.index("{'page':0}").compose(RxHttpResponseCompat.<RecommendBean>compatResult(((RecommendFragment)view).getActivity()));
                        }else{
                            view.onRequestPermissionError();
                            return Observable.empty();
                        }
                    }
                })
                .subscribe(new Consumer<RecommendBean>() {
                    @Override
                    public void accept(RecommendBean appInfo) throws Exception {
                        Log.e("TAG", "new Consumer<AppInfo>()");
                        mRecommendBean = appInfo;//用作完成时的判断
                        if(appInfo != null) {
                            view.onRequestPermissionSuccess();
                            view.showRecyclerView(appInfo);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", "出错");
                        view.showNetError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestDatas();
                            }
                        });
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e("TAG", "结束");
                        if(mRecommendBean.getBanners().size() == 0 &&
                                mRecommendBean.getRecommendApps().size() == 0 && mRecommendBean.getRecommendGames().size() == 0) {
                            view.showEmpty(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    requestDatas();
                                }
                            });
                        }else{
                            view.dimissLoading();
                        }

                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e("TAG", "准备工作");
                        view.showLodading();
                    }
                });


//        mApiService.index("{'page':0}")
//                .compose(RxHttpResponseCompat.<AppInfo>compatResult())
//                .subscribe(new Consumer<AppInfo>() {
//                    @Override
//                    public void accept(AppInfo appInfo) throws Exception {
//                        Log.e("TAG", "new Consumer<AppInfo>()");
//                        mInfo = appInfo;
//                        mStatus = appInfo.getStatus();
//                        if("success".equals(appInfo.getMessage())) {
//                            mDatasBeen = appInfo.getDatas();
//                            if(mDatasBeen != null && mDatasBeen.size() > 0) {
//                                view.showRecyclerView(mDatasBeen);
//                            }
//                        }else{
//                            view.showEmpty(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    requestDatas();
//                                }
//                            });
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e("TAG", "出错");
//                        //view.showErrorMessage(throwable.getMessage());
//                        view.showNetError(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                requestDatas();
//                            }
//                        });
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        Log.e("TAG", "结束");
//                        if("success".equals(mInfo.getMessage())) {
//                            view.restoreView();
//                        }else{
//                            view.showEmpty(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    requestDatas();
//                                }
//                            });
//                        }
//
//                    }
//                }, new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                        Log.e("TAG", "准备工作");
//                        view.showLodading();
//                    }
//                });

    }

//    public void requestPermission(){
//        RxPermissions rxPermissions = new RxPermissions(((Fragment) view).getActivity());
//        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if(aBoolean) {
//                            view.onRequestPermissionSuccess();
//                        }else{
//                            view.onRequestPermissionError();
//                        }
//                    }
//                });
//    }

}
