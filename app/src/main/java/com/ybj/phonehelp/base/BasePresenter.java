package com.ybj.phonehelp.base;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 业务逻辑处理的基础类（带完善）
 */

public interface BasePresenter<T> {

    void attachView(T view);

    void detachView();

}
