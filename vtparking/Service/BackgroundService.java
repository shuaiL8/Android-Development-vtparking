package com.example.fredliu.vtparking.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.example.fredliu.vtparking.R;

import java.util.List;
import java.util.Map;

/**
 * Created by fredliu on 12/8/17.
 */

public class BackgroundService extends Service {


    private String[] content = new String[3];
    private String[] time = new String[3];
    private final IBinder mBinder = new LocalBinder();
    private Notification notification;
    private NotificationManager manager;
    private Handler handler;



    public class LocalBinder extends Binder {
        public BackgroundService getService() {
            return BackgroundService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case R.id.toggleButton:
                        List<Map<String,Object>> unusualCarList = (List<Map<String,Object>>)msg.getData().getSerializable("unusualCarList");
                        if(unusualCarList==null||unusualCarList.size()<1)
                            return;
                        showNotification(unusualCarList);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        //请求数据
//        CarController.queryUnusualCar(intent.getStringExtra("carUserId"), handler);
    }
    //弹出Notification
    private void showNotification(List<Map<String,Object>> unusualCarList) {
        final Bitmap largeIcon = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_notifications_black_24dp)).getBitmap();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, new Intent().setAction("intvehapp.intvehapp.Activity.BaiDuMapActivity"), 0);
        notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setLargeIcon(largeIcon)
                .setTicker("新消息！！")
                .setContentTitle("新消息！！！！")
                .setContentText("新消息~")
                .setContentIntent(pendingIntent3).setNumber(1).build(); // 需要注意build()是在API
        // level16及之后增加的，API11可以使用getNotificatin()来替代
        notification.flags |= Notification.FLAG_AUTO_CANCEL; // FL
        manager.notify(1, notification);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }



}
