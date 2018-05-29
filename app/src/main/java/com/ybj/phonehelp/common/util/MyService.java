package com.ybj.phonehelp.common.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.ybj.phonehelp.common.AppApplication;

/**
 * Created by wenmingvs on 2016/1/13.
 */
public class MyService extends Service {

    private static final float UPDATA_INTERVAL = 2.0f;//in seconds
    private String status = "第一次进来";
    private Context mContext;
    private AlarmManager manager;

//    private Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            switch (msg.what) {
//                case 1 :
//                    Log.e("Uart", "结束MainActivity");
//                    MainActivity.mMainActivity.finish();
//                    break;
//            }
//        }
//    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Uart", "服务被开启了");

        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int updateTime = (int) UPDATA_INTERVAL * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + updateTime;
        Intent i = new Intent(mContext, MyReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

        status = getAppStatus() ? "前台" : "后台";

        if ("后台".equals(status)) {
            Message message = new Message();
            message.what = 1;
            String messageName = AppApplication.get(mContext).handler.getMessageName(message);
            AppApplication.get(mContext).handler.sendMessageDelayed(message, 20000);
        } else {
            AppApplication.get(mContext).handler.removeMessages(1);
        }

        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private boolean getAppStatus() {
        return BackgroundUtil.isForeground(mContext, Features.BGK_METHOD, mContext.getPackageName());
    }


}
