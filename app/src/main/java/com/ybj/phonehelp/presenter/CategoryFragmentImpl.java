package com.ybj.phonehelp.presenter;

import android.view.View;

import com.ybj.phonehelp.bean.CategoryBean;
import com.ybj.phonehelp.common.rx.RxHttpResponseCompat;
import com.ybj.phonehelp.http.ApiService;
import com.ybj.phonehelp.presenter.contract.CategoryContract;
import com.ybj.phonehelp.ui.fragment.CategoryFragment;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by 杨阳洋 on 2018/1/21.
 * 分类页面逻辑处理
 */

public class CategoryFragmentImpl implements CategoryContract.Presenter {

    private ApiService mApiService;
    private CategoryContract.View mView;
    private String mMessage;

    public CategoryFragmentImpl(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public void attachView(CategoryContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void requestDatas() {
        mApiService.category()
                .compose(RxHttpResponseCompat.<List<CategoryBean>>compatResult(((CategoryFragment) mView).getActivity()))
                .subscribe(new Consumer<List<CategoryBean>>() {
                    @Override
                    public void accept(List<CategoryBean> categoryBean) throws Exception {
                        mView.showRecyclerView(categoryBean);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestDatas();
                            }
                        });
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.dimissLoading();
                    }
                }, new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLodading();
                    }
                });
    }
}
