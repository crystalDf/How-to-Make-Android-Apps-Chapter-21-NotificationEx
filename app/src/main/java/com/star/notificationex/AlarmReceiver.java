package com.star.notificationex;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    public static final int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotification(context, "Times Up", "5 Seconds has passed", "Alert");
    }

    private void createNotification(Context context, String msgTitle,
                                    String msgText, String msgAlert) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setContentTitle(msgTitle)
                        .setContentText(msgText)
                        .setTicker(msgAlert)
                        .setSmallIcon(R.mipmap.tmntraph)
                        .setContentIntent(pendingIntent)
                        .setDefaults(NotificationCompat.DEFAULT_SOUND)
                        .setAutoCancel(true);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
