package com.ybj.phonehelp.common.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

/**
 * Created by 杨阳洋 on 2018/1/8.
 * 网络工具类
 */

public class NetWorkUtils {

    public enum NetType {
        WIFI, CMNET, CMWAP, NONE
    }

    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetWorkAvailable(Context context) {
        ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] infos = mgr.getAllNetworkInfo();//获得所有的网络的信息
        if (infos != null) {
            for (int i = 0; i < infos.length; i++) {
                if (infos[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断当前网络是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mgr.getActiveNetworkInfo();//返回当前网络的详细信息
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断WIFI是否连接
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetworkInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetworkInfo != null) {
                return wifiNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 判断当前是否是手机连接
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 返回当前的连接的类型
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = mgr.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return activeNetworkInfo.getType();
            }
        }
        return -1;
    }

    /**
     * 返回当前的连接类型和上面方法是一样的
     */
    public static NetType getAPNType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return NetType.NONE;
        }
        int nType = networkInfo.getType();

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            if (networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")) {
                return NetType.CMNET;
            } else {
                return NetType.CMWAP;
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.WIFI;
        }
        return NetType.NONE;
    }

}
