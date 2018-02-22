package com.ybj.phonehelp.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.bean.ViewEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * App详情页面
 * 如何让列表点击项突出显示
 * 通过透明度或者设置动画效果
 */

public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.rl)
    RelativeLayout mRl;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        //注册RxBus
        EventBus.getDefault().register(this);

    }

    /**
     * 接收数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getView(ViewEvent view){
        Bitmap bitmap = getViewImageCache(view.getView());
        if(bitmap != null) {
            mImg.setImageBitmap(bitmap);
        }
    }

    /**
     * 获取View的缓存图像显示
     * @param view
     * @return
     */
    private Bitmap getViewImageCache(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if(bitmap == null) {
            return null;
        }
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());

        view.destroyDrawingCache();

        return bitmap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }
}