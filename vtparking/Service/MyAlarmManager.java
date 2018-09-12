package com.example.fredliu.vtparking.Service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by fredliu on 12/8/17.
 */


public class MyAlarmManager
{
    //开启轮询服务
    public static void startPollingService(Context context, int seconds, Class<?> cls, String carUserId) {
        //获取AlarmManager系统服务
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Log.e("startPollingService:","开启轮询服务");
        Intent intent = new Intent(context, cls);
        intent.putExtra("carUserId",carUserId);//添加需要传递的一些参数
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//我是用的是service
        //使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
        manager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime(), seconds * 1000, pendingIntent);

    }

    //停止轮询服务
    public static void stopPollingService(Context context, Class<?> cls,String action) {
        AlarmManager manager = (AlarmManager) context
                .getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //取消正在执行的服务
        manager.cancel(pendingIntent);
    }
}
