package com.ybj.phonehelp.common.exception;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ybj.phonehelp.ui.activity.LoginActivity;

public class ApiException extends BaseException {

    public ApiException(int code, String displayMessage, Context context) {
        super(code, displayMessage);
        if(code == 10010) {//Token失效的处理
            Toast.makeText(context, "登录失效", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }
}
