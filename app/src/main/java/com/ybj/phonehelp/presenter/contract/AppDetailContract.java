package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.AppDetailsBean;

/**
 * Created by 杨阳洋 on 2018/2/25.
 * APP详情页面的P层
 */

public interface AppDetailContract {

    interface View extends BaseView<AppDetailsBean>{
        /**
         * App详情页面展示
         * @param appDetailsBean
         */
        void showAppDetail(AppDetailsBean appDetailsBean);
    }

    interface Presenter extends BasePresenter<AppDetailContract.View>{
        /**
         * 请求数据
         */
        void requestDatas(int id);
    }

}
