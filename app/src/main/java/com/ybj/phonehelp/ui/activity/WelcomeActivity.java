package com.ybj.phonehelp.ui.activity;

import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;

import com.eftimoff.androipathview.PathView;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.common.config.Constant;
import com.ybj.phonehelp.common.util.ACache;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.pathView)
    PathView mPathView;
    @BindView(R.id.activity_welcome)
    LinearLayout mActivityWelcome;

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        mPathView.getPathAnimator()
                .delay(200)
                .duration(3000)
                .interpolator(new AccelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();
                    }
                })
                .start();
    }

    private void jump() {
        if("0".equals(ACache.get(this).getAsString(Constant.IS_SHOW_GUIDE))) {
            startActivity(MainActivity.class);
        }else{
            startActivity(GuideActivity.class);
        }
        finish();
    }

}
