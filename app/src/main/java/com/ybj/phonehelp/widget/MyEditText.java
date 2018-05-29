package com.ybj.phonehelp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by 杨阳洋 on 2018/5/16.
 */

public class MyEditText extends EditText implements Serializable{
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        event.setPassword(true);
    }

}
