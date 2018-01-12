package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.RankingBean;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */

public interface RankingContract {

    interface View extends BaseView<RankingBean>{

        /**
         * 停止加载更多
         */
        void onLoadMoreComplete();

    }

    interface Presenter extends BasePresenter<View>{
        /**
         * 请求数据
         */
        void requestDatas(int page);
    }

}
