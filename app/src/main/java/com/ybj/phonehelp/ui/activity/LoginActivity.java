package com.ybj.phonehelp.ui.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.bean.LoginBean;
import com.ybj.phonehelp.common.util.VerificationUtils;
import com.ybj.phonehelp.dagger2.component.DaggerLoginComponent;
import com.ybj.phonehelp.dagger2.module.activity.LoginModule;
import com.ybj.phonehelp.presenter.LoginActivityImpl;
import com.ybj.phonehelp.presenter.contract.LoginContract;
import com.ybj.phonehelp.widget.LoadingButton;

import javax.inject.Inject;

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

public class LoginActivity extends BaseActivity implements LoginContract.View{


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

    @Inject
    LoginActivityImpl mLoginActivityImpl;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule())
                .build().inject(this);
    }

    @Override
    public void init() {

        mLoginActivityImpl.attachView(this);
        initView();
        initListener();

    }

    private void initListener() {
        RxView.clicks(mBtnLogin)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        if(!VerificationUtils.matcherPhoneNum(mTxtMobi.getText().toString().trim())) {
                            mViewMobiWrapper.setError("手机号码格式不正确");
                        }else{
                            mViewMobiWrapper.setError(null);
                            mViewMobiWrapper.setEnabled(true);
                            mLoginActivityImpl.requestDatas(mTxtMobi.getText().toString().trim(),
                                    mTxtPassword.getText().toString().trim());
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

    @Override
    public void showLodading() {
        mBtnLogin.showLoading();
    }

    @Override
    public void dimissLoading() {
        mBtnLogin.showButtonText();
    }

    @Override
    public void showRecyclerView(LoginBean loginBean) {

    }

    @Override
    public void showEmpty(View.OnClickListener listener) {

    }

    @Override
    public void restoreView() {

    }

    /**
     * 网络错误提示
     * @param listener
     */
    @Override
    public void showNetError(View.OnClickListener listener) {

    }

    /**
     * 登录成功的回调
     */
    @Override
    public void onSuccessMsg(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 此处只需要提示密码不对
     */
    @Override
    public void onDefaultMsg(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

}
