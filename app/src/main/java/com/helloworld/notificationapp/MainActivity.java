package com.helloworld.notificationapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;

    enum EnNotification {
        VIBRATION, SOUND, ALL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        bindViews();
    }

    private void bindViews() {
        findViewById(R.id.button_normal).setOnClickListener(this);
        findViewById(R.id.button_big_icon).setOnClickListener(this);
        findViewById(R.id.button_launcher).setOnClickListener(this);
        findViewById(R.id.button_sound).setOnClickListener(this);
        findViewById(R.id.button_vibration).setOnClickListener(this);
        findViewById(R.id.button_all).setOnClickListener(this);
        findViewById(R.id.button_big_style).setOnClickListener(this);
        findViewById(R.id.button_picture_style).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_normal:
                normalNotification();
                break;
            case R.id.button_big_icon:
                bigIconNotification();
                break;
            case R.id.button_launcher:
                launcherAppNotification();
                break;
            case R.id.button_sound:
                setDefaultsNotification(EnNotification.SOUND);
                break;
            case R.id.button_vibration:
                setDefaultsNotification(EnNotification.VIBRATION);
                break;
            case R.id.button_all:
                setDefaultsNotification(EnNotification.ALL);
                break;
            case R.id.button_big_style:
                bigTextStyleNotification();
                break;
            case R.id.button_picture_style:
                pictureStyleNotification();
                break;
        }
    }

    private void normalNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("タイトル");
        builder.setContentText("内容");
        builder.setContentInfo("情報欄");
        builder.setTicker("通知概要");
        NotificationManager manager =
                (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void bigIconNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("大きいアイコンで通知");
        // 大きいアイコンを表示するためにBitmapを設定する
        Bitmap bigIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bigIcon);
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void setDefaultsNotification(EnNotification notification) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        switch (notification) {
            case VIBRATION:
                builder.setContentTitle("VIBRATION");
                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                break;
            case SOUND:
                builder.setContentTitle("SOUND");
                builder.setDefaults(Notification.DEFAULT_SOUND);
                break;
            case ALL:
                builder.setContentTitle("ALL");
                builder.setDefaults(Notification.DEFAULT_ALL);
                break;
        }
        builder.setWhen(System.currentTimeMillis());
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void launcherAppNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("タップでアプリを起動する");

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void bigTextStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // スタイル設定
        NotificationCompat.BigTextStyle bigTextStyle =
                new NotificationCompat.BigTextStyle(builder);
        bigTextStyle.setBigContentTitle("BigTextStyle");
        bigTextStyle.bigText("コンテンツサンプルコンテンツサンプル");
        bigTextStyle.setSummaryText("通知サマリテキスト");

        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void pictureStyleNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        // スタイル設定
        NotificationCompat.BigPictureStyle bigPictureStyle =
                new NotificationCompat.BigPictureStyle(builder);
        Bitmap largePicture = BitmapFactory.decodeResource(
                getResources(), R.mipmap.ic_launcher);
        bigPictureStyle.bigPicture(largePicture);
        bigPictureStyle.setBigContentTitle("BigPictureStyle");
        bigPictureStyle.setSummaryText("通知サマリテキスト");

        NotificationManager manager = (NotificationManager)
                getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
