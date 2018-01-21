package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.LoginBean;

/**
 * Created by 杨阳洋 on 2018/1/21.
 */

public interface LoginContract {

    interface View extends BaseView<LoginBean> {
        /**
         * 登录成功提示
         */
        void onSuccessMsg(String msg);

        /**
         * 登录失败提示
         */
        void onDefaultMsg(String msg);
    }

    interface Presenter extends BasePresenter<LoginContract.View> {
        /**
         * 请求数据
         */
        void requestDatas(String email,String password);
    }

}
