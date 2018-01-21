package com.ybj.phonehelp.presenter.contract;

import com.ybj.phonehelp.base.BasePresenter;
import com.ybj.phonehelp.base.BaseView;
import com.ybj.phonehelp.bean.CategoryBean;

import java.util.List;

/**
 * Created by 杨阳洋 on 2018/1/21.
 * 分类列表视图跟特有的逻辑处理
 */

public interface CategoryContract {

    interface View extends BaseView<List<CategoryBean>> {

    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 请求数据
         */
        void requestDatas();
    }

}
