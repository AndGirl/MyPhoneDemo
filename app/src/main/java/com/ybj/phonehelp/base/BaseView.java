package com.ybj.phonehelp.base;

/**
 * Created by 杨阳洋 on 2017/12/30.
 * 基础页面的展示带完善
 */

public interface BaseView<T> {

    void showLodading();

    void dimissLoading();

    void showErrorMessage(String msg);

    void showRecyclerView(T t);
}
