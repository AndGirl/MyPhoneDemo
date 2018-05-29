package com.ybj.phonehelp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.bean.ViewEvent;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.DensityUtil;
import com.ybj.phonehelp.imageloader.ImageLoader;
import com.ybj.phonehelp.ui.fragment.AppDetailFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * App详情页面
 * 如何让列表点击项突出显示
 * 通过透明度或者设置动画效果
 */

public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.img_icon)
    ImageView mImgIcon;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.txt_name)
    TextView mTxtName;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout mViewCoordinator;
    @BindView(R.id.rl)
    LinearLayout mRl;
    @BindView(R.id.view_temp)
    View mViewTemp;
    private int mDatasBean;

    @BindView(R.id.content_view)
    FrameLayout mContentView;
    private ObjectAnimator mAnimator;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void init() {
        mDatasBean = getIntent().getIntExtra("appInfoId", 0);
        String appTitle = getIntent().getStringExtra("appTitle");
        String appIcon = getIntent().getStringExtra("appIcon");

        ImageLoader.load(Constant.BASE_IMAGEURL + appIcon, mImgIcon);
        mTxtName.setText(appTitle);

        initToolBar();

        //注册RxBus
        EventBus.getDefault().register(this);
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        mToolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(GoogleMaterial.Icon.gmd_keyboard_backspace)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)));

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 接收数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getView(ViewEvent view) {
        View view1 = view.getView();
        Bitmap bitmap = getViewImageCache(view1);
        if (bitmap != null) {
            mViewTemp.setBackgroundDrawable(new BitmapDrawable(bitmap));
        }

        int[] location = new int[2];
        //getLocationOnScreen():一个控件在整个屏幕中的位置
        view1.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];

        ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(mViewTemp.getLayoutParams());
        marginLayoutParams.topMargin = top - DensityUtil.getStatusBarH(this);
        marginLayoutParams.leftMargin = left;
        marginLayoutParams.width = view1.getWidth();
        marginLayoutParams.height = view1.getHeight();

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginLayoutParams);
        mViewTemp.setLayoutParams(params);

        openInterfaceView();
    }

    /**
     * 获取View的缓存图像显示
     *
     * @param view
     * @return
     */
    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap == null) {
            return null;
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());

        view.destroyDrawingCache();

        return bitmap;
    }

    /**
     * 通过属性动画实现View向上下展开
     */
    public void openInterfaceView() {
        int height = DensityUtil.getScreenH(this) + DensityUtil.getStatusBarH(this);
        mAnimator = ObjectAnimator.ofFloat(mViewTemp, "scaleY", 1f, height);
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mViewTemp.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mViewTemp.setVisibility(View.GONE);
                mViewCoordinator.setVisibility(View.VISIBLE);
                initFragment();
            }

        });
        mAnimator.setStartDelay(500);
        mAnimator.setDuration(1000);
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止Activity的onDestroy()方法执行的时候，会初始化Fragment页面
        mAnimator.removeAllListeners();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    private void initFragment() {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.content_view, AppDetailFragment.newInstance(mDatasBean));
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
