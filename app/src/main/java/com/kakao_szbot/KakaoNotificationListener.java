package com.kakao_szbot;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class KakaoNotificationListener extends NotificationListenerService {
    public final static String TAG = "NotificationListener";

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);

        Log.d(TAG, "onNotificationRemoved ~ " +
                "\n packageName: " + sbn.getPackageName() +
                "\n id: " + sbn.getId());
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        if (!sbn.getPackageName().contains("com.kakao.talk")) return;

        Notification notification = sbn.getNotification();
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);
        Icon smallIcon = notification.getSmallIcon();
        Icon largeIcon = notification.getLargeIcon();

        Log.d(TAG, "onNotificationPosted ~ " +
                "\n packageName: " + sbn.getPackageName() +
                "\n id: " + sbn.getId() +
                "\n postTime: " + sbn.getPostTime() +
                "\n title: " + title +
                "\n text : " + text +
                "\n subText: " + subText);

        KakaoSendReply("hi", sbn);
    }

    public boolean KakaoSendReply(String replyMessage, StatusBarNotification sbn){
        if (replyMessage == null) return false;

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        Notification.Action[] actions = sbn.getNotification().actions;

        if (actions == null) {
            Log.d(TAG, "sbn.getNotification().actions null");
            return false;
        }
        Log.d(TAG, "sbn.getNotification().actions length: " + actions.length);

        for (Notification.Action a : actions) {
            RemoteInput[] remoteInputs = a.getRemoteInputs();
            if (remoteInputs == null) {
                Log.d(TAG, "action(" + a.title + ") getRemoteInputs() null");
                continue;
            }
            Log.d(TAG, "remoteInputs length: " + remoteInputs.length);

            try {
                for (int i = 0; i < remoteInputs.length; i++) {
                    bundle.putCharSequence(remoteInputs[i].getResultKey(), replyMessage);
                }
                RemoteInput.addResultsToIntent(remoteInputs, intent, bundle);
                a.actionIntent.send((Context) this, 0, intent);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
