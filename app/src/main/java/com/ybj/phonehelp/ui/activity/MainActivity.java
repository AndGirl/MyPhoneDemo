package com.ybj.phonehelp.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.bean.LoginBean;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.ACache;
import com.ybj.phonehelp.common.util.Cniao5Font;
import com.ybj.phonehelp.ui.adapter.ViewPagerAdapter;

import java.lang.reflect.Field;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class MainActivity extends BaseActivity {


    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.navigationView)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private long mTime;
    private TextView mTextUserName;
    private ImageView mUserHeadView;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        //注册RxBus
        RxBus.get().register(this);

        initListener();

        initTabLayout();

        login();
    }

    /**
     * 第一次进来是否登录的判断
     */
    private void login() {
        Object asObject = ACache.get(MainActivity.this).getAsObject(Constant.USER);
        if(asObject != null) {
            LoginBean.UserBean userBean = (LoginBean.UserBean) asObject;
            initUserHeadView(userBean);
        }
    }

    private void initTabLayout() {
        mAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        //解决预加载问题
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);

        setIndicator(mTabLayout, 20, 20);
    }

    private void initListener() {
        mDrawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.e("TAG", "onDrawerSlide");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.e("TAG", "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.e("TAG", "onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                Log.e("TAG", "onDrawerStateChanged");
            }
        });

        View headerView = mNavigationView.getHeaderView(0);

        //头部信息设置
        mUserHeadView = (ImageView) headerView.findViewById(R.id.header_img);
        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.md_white_1000));
        mTextUserName = (TextView) headerView.findViewById(R.id.txt_username);

        //设置子菜单项图标
        mNavigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_loop));
        mNavigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_download));
        mNavigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_delete));
        mNavigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_settings));
        mNavigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, Cniao5Font.Icon.cniao_shutdown));

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Log.e("TAG", "更新");
                        break;
                    case R.id.menu_download_manager:
                        Log.e("TAG", "消息");
                        break;
                    case R.id.menu_app_uninstall:
                        Log.e("TAG", "应用卸载");
                        break;
                    case R.id.menu_setting:
                        Log.e("TAG", "设置");
                        break;
                    case R.id.menu_logout:
                        Log.e("TAG", "退出登录");
                        loginOut();
                        break;
                }

                return false;
            }
        });

        mUserHeadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object asObject = ACache.get(MainActivity.this).getAsObject(Constant.USER);
                if(asObject != null) {
                    LoginBean.UserBean userBean = (LoginBean.UserBean) asObject;
                    initUserHeadView(userBean);
                }else{
                    startActivity(LoginActivity.class);
                }
            }
        });

        mToolbar.inflateMenu(R.menu.toolbar_menu);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer
                , mToolbar, R.string.open, R.string.close);
        drawerToggle.syncState();
        mDrawer.addDrawerListener(drawerToggle);
    }

    /**
     * 固定下划线宽度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mTime > 2000) {
                Toast.makeText(MainActivity.this, "再按一次返回桌面", Toast.LENGTH_SHORT).show();
                mTime = System.currentTimeMillis();
            } else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
                //System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 接收数据
     */
    @Subscribe
    public void getUser(LoginBean.UserBean user){
        initUserHeadView(user);
    }

    /**
     * 初始化头部信息
     * @param user
     */
    private void initUserHeadView(LoginBean.UserBean user) {

        Glide.with(this).load(user.getLogo_url())
                .bitmapTransform(new CropCircleTransformation(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        Log.e("TAG", "===============onException==============");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        Log.e("TAG", "onResourceReady");
                        return false;
                    }
                })
                .into(mUserHeadView);

        mTextUserName.setText(user.getUsername());

    }

    /**
     * 退出登录
     */
    private void loginOut(){
        ACache aCache = ACache.get(this);

        aCache.put(Constant.TOKEN,"");
        aCache.put(Constant.USER,"");

        mUserHeadView.setImageDrawable(new IconicsDrawable(this, Cniao5Font.Icon.cniao_head).colorRes(R.color.md_white_1000));
        mTextUserName.setText("未登录");

        mUserHeadView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });

        Toast.makeText(MainActivity.this,"您已退出登录",Toast.LENGTH_LONG).show();
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁RxBus
        RxBus.get().unregister(this);
    }
}
