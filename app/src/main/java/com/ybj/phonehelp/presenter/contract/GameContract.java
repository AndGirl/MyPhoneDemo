package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.GameBean;

/**
 * Created by 杨阳洋 on 2018/1/16.
 * 控制游戏界面特有的UI和逻辑处理
 */

public interface GameContract {

    interface View extends BaseView<GameBean>{
        /**
         * 停止加载更多
         */
        void onLoadMoreComplete();
    }

    interface Presenter extends BasePresenter<View>{
        /**
         * 请求数据
         */
        void requestDatas(int page,boolean isLoading);
    }

}
