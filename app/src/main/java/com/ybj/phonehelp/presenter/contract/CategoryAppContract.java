package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.GameBean;

/**
 * Created by 杨阳洋 on 2018/1/23.
 */

public interface CategoryAppContract {

    interface View extends BaseView<GameBean> {
        /**
         * 停止加载更多
         */
        void onLoadMoreComplete();
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 请求数据
         * categoryIda,page请求接口需要
         * flagType:判断请求的具体接口
         * isLoading:是否加载更多
         */
        void requestDatas(int categoryId,int page,int flagType,boolean isLoading);
    }

}
