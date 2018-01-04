package com.ybj.phonehelp.presenter;

import com.ybj.phonehelp.presenter.contract.GuideContract;

/**
 * Created by 杨阳洋 on 2018/1/4.
 * 处理GuideFragment的业务逻辑
 */

public class GuideFragmentImpl implements GuideContract.Presenter{

    private GuideContract.View view;

    @Override
    public void attachView(GuideContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void setBtnDatas() {
        view.showFragment();
    }
}
