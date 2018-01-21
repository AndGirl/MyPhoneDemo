package com.ybj.phonehelp.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.widget.LoadingButton;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * 登录页面
 * RxBinding的使用
 * http://blog.csdn.net/niubitianping/article/details/56278502
 */

public class LoginActivity extends BaseActivity {


    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.txt_mobi)
    EditText mTxtMobi;
    @BindView(R.id.view_mobi_wrapper)
    TextInputLayout mViewMobiWrapper;
    @BindView(R.id.txt_password)
    EditText mTxtPassword;
    @BindView(R.id.view_password_wrapper)
    TextInputLayout mViewPasswordWrapper;
    @BindView(R.id.btn_login)
    LoadingButton mBtnLogin;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        initView();
        initListener();

    }

    private void initListener() {
        RxView.clicks(mBtnLogin)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if(!mTxtMobi.getText().toString().trim().equals("15390264297")) {
                            mViewMobiWrapper.setError("手机号码输入错误");
                        }else{
                            mViewMobiWrapper.setError(null);
                            mViewMobiWrapper.setEnabled(false);
                        }
                    }
                });

        RxTextView.textChanges(mTxtMobi)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                        mViewMobiWrapper.setError(null);
                    }
                });

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {

        Observable<CharSequence> observableEmail = RxTextView.textChanges(mTxtMobi);
        Observable<CharSequence> observablePassword = RxTextView.textChanges(mTxtPassword);

        //前两个参数代表传入的值，第三个参数代表返回的值
        Observable.combineLatest(observableEmail, observablePassword, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(@NonNull CharSequence observableEmail, @NonNull CharSequence observablePassword) throws Exception {
                return isPhoneValid(observableEmail.toString()) && isPasswordValid();
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                RxView.enabled(mBtnLogin).accept(aBoolean);
            }
        });

        mToolBar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(GoogleMaterial.Icon.gmd_keyboard_backspace)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)));

    }

    /**
     * 判断账号是否11位
     * @param phone
     * @return
     */
    private boolean isPhoneValid(String phone){
        return mTxtMobi.length() == 11;
    }

    /**
     * 判断密码长度是否大于6
     * @return
     */
    private boolean isPasswordValid(){
        return mTxtPassword.length() >= 6;
    }

}
