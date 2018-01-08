package com.ybj.phonehelp.common.config;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ybj.phonehelp.R;

/**
 * Created by 杨阳洋 on 2018/1/8.
 * 加载框的控制类 主要功能实现在parent中view的添加和恢复原来的parentView
 */

public class VaryViewManager {

    private VaryViewHelper mVaryViewHelper;

    /**
     * 初始化的时候就将view传入进去
     */
    public VaryViewManager(View view) {
        mVaryViewHelper = new VaryViewHelper(view);
    }


    /**
     * 显示加载内容为空
     */
    public void showEmpty( View.OnClickListener onClickListener) {
        View layout = mVaryViewHelper.inflate(R.layout.layout_view_load_message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(mVaryViewHelper.getContext().getResources().getString(R.string.error_system));
        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }
        mVaryViewHelper.showLayout(layout);

    }

    /**
     * 显示正在加载
     */
    public void showLoading() {
        View layout = mVaryViewHelper.inflate(R.layout.layout_view_loading);
        mVaryViewHelper.showLayout(layout);
    }


    /**
     * 显示网络异常 并显示相应的的布局
     */
    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = mVaryViewHelper.inflate(R.layout.layout_view_load_message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(mVaryViewHelper.getContext().getResources().getString(R.string.no_network_msg));
        if (null != onClickListener) {
            //手势监听 滑动或者点击才重新加载
            layout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            layout.setOnClickListener(onClickListener);
        }
        mVaryViewHelper.showLayout(layout);
    }

    /**
     * 重置ui
     */
    public void restore() {
        mVaryViewHelper.restoreView();
    }

    /**
     * 内部类，主要负责View的添加与移除
     */
    public class VaryViewHelper {

        private View mView;
        private View mCurrentView;
        private ViewGroup.LayoutParams mParams;
        private ViewGroup mParentView;
        private int mViewIndex;


        public VaryViewHelper(View view) {
            super();
            this.mView = view;
        }

        /**
         * 默认初始化操作
         * 1.获得当前的ParentView
         * 2.获得当前view所在的位置
         * 3.获得当前view
         */
        private void init() {
            mParams = mView.getLayoutParams();
            if (mView.getParent() != null) {
                mParentView = (ViewGroup) mView.getParent();

            } else {
                mParentView = (ViewGroup) mView.getRootView();
            }
            int count = mParentView.getChildCount();
            for (int i = 0; i < count; i++) {
                if (mView == mParentView.getChildAt(i)) {
                    mViewIndex = i;
                }
            }
            mCurrentView = mView;
        }


        /**
         * 获得当前view
         */
        public View getCurrentLayout() {
            return mCurrentView;
        }

        /**
         * 恢复原来的view
         */
        public void restoreView() {
            showLayout(mView);
        }

        /**
         * 显示相应的布局
         * 动态的加载相应的
         *
         * @param view
         */
        public void showLayout(View view) {
            if (mParentView == null) {
                init();
            }
            this.mCurrentView = view;
            //如果已经是那个view,就不需要在进行替换操作了
            if (mParentView.getChildAt(mViewIndex) != view) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
                mParentView.removeViewAt(mViewIndex);
                mParentView.addView(view, mViewIndex, mParams);
            }

        }

        /**
         * 通过id来获取加载相应的布局
         */
        public View inflate(int layoutId) {
            return LayoutInflater.from(mView.getContext()).inflate(layoutId, null);
        }

        /**
         * 获得上下文对象
         */
        public Context getContext() {
            return mView.getContext();
        }

        /**
         * 获取当前的view
         */
        public View getView() {
            return mView;
        }
    }

}
