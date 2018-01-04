package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;

/**
 * Created by 杨阳洋 on 2018/1/4.
 * 控制GuideFragment特有的UI和逻辑处理
 */

public interface GuideContract {

    interface View extends BaseView<Object>{

        void showFragment();

    }

    interface Presenter extends BasePresenter<View>{
        void setBtnDatas();
    }

}
