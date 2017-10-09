package com.LeelaGroup.AgrawalFedration;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by USer on 11-09-2017.
 */

public class FcmMessagingServices extends FirebaseMessagingService {
    String title,message,icon,click_action;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        title = remoteMessage.getNotification().getTitle();
        message = remoteMessage.getNotification().getBody();
        click_action = remoteMessage.getNotification().getClickAction();

        Intent intent = new Intent(click_action);

        intent.putExtra("title", title);
        intent.putExtra("message", message);
        intent.putExtra("icon", icon);

        //LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
        //localBroadcastManager.sendBroadcast(intent);


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(getBitmapFromURL(icon)));

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
        super.onMessageReceived(remoteMessage);
    }
}
