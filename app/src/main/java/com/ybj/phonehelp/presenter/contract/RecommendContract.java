package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.RecommendBean;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 控制RecommendFragment特有的UI和逻辑处理
 */

public interface RecommendContract {

    interface View extends BaseView<RecommendBean>{

        void onRequestPermissionSuccess();
        void onRequestPermissionError();

    }

    interface Presenter extends BasePresenter<View>{

        void requestDatas();

    }
}
