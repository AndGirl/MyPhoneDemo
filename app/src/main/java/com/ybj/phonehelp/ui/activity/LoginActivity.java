package com.ybj.phonehelp.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.ybj.phonehelp.R;
import com.ybj.phonehelp.base.AppComponent;
import com.ybj.phonehelp.base.BaseActivity;
import com.ybj.phonehelp.common.util.DeviceUtils;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 暂时用来测试权限
 */

public class LoginActivity extends BaseActivity {

    private static final int READ_PHONE_STATE_CODE = 1000;

    @BindView(R.id.btn)
    Button mBtn;

    @Override
    public int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rxPermissions = new RxPermissions(LoginActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if(aBoolean) {
                                    String imei = DeviceUtils.getIMEI(LoginActivity.this);
                                    Toast.makeText(LoginActivity.this, "imei = " + imei, Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, "用户授权拒绝", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
}
        });
                }

@OnClick(R.id.btn)
    public void onViewClicked() {
        //没有授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_CODE);
        } else {
            //已经授权
            String imei = DeviceUtils.getIMEI(this);
            Toast.makeText(LoginActivity.this, "imei = " + imei, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == READ_PHONE_STATE_CODE) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                String imei = DeviceUtils.getIMEI(this);
                Toast.makeText(LoginActivity.this, "imei = " + imei, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this, "用户授权拒绝", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
