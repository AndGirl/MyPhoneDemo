package com.ybj.phonehelp.common.util;

import android.app.Service;
import android.content.Context;

import com.ybj.phonehelp.common.AppApplication;

/**
 * Created by wenmingvs on 2016/1/14.
 */
public class BackgroundUtil {
    public static final int BKGMETHOD_GETAPPLICATION_VALUE = 2;

    /**
     * 自动根据参数选择判断前后台的方法
     *
     * @param context     上下文参数
     * @param packageName 需要检查是否位于栈顶的App的包名
     * @return
     */
    public static boolean isForeground(Context context, int methodID, String packageName) {
        switch (methodID) {
            case BKGMETHOD_GETAPPLICATION_VALUE:
                return getApplicationValue((AppApplication) ((Service) context).getApplication());
            default:
                return false;
        }
    }


    /**
     * 通过ActivityLifecycleCallbacks来批量统计Activity的生命周期，来做判断，此方法在API 14以上均有效，但是需要在Application中注册此回调接口
     * 必须：
     * 1. 自定义Application并且注册ActivityLifecycleCallbacks接口
     * 2. AndroidManifest.xml中更改默认的Application为自定义
     * 3. 当Application因为内存不足而被Kill掉时，这个方法仍然能正常使用。虽然全局变量的值会因此丢失，但是再次进入App时候会重新统计一次的
     * @param myApplication
     * @return
     */

    public static boolean getApplicationValue(AppApplication myApplication) {
                return myApplication.getAppCount() > 0;
    }

}
