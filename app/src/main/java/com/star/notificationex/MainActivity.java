package com.star.notificationex;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.GregorianCalendar;


public class MainActivity extends AppCompatActivity {

    private Button mShowNotificationButton;
    private Button mStopNotificationButton;
    private Button mSetAlarmButton;

    public static final int NOTIFICATION_ID = 333;

    public static final int REQUEST_CODE = 0;

    private boolean isNotificationActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowNotificationButton = (Button) findViewById(R.id.show_notification);
        mStopNotificationButton = (Button) findViewById(R.id.stop_notification);
        mSetAlarmButton = (Button) findViewById(R.id.set_alarm);

        mShowNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("Message")
                                .setContentText("New Message")
                                .setTicker("Alarm New Message")
                                .setSmallIcon(R.mipmap.tmntraph);

                Intent intent = new Intent(MainActivity.this, MoreInfoNotificationActivity.class);

                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MainActivity.this);

                taskStackBuilder.addParentStack(MoreInfoNotificationActivity.class);

                taskStackBuilder.addNextIntent(intent);

                PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(NOTIFICATION_ID, builder.build());

                isNotificationActive = true;
            }
        });

        mStopNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNotificationActive) {
                    NotificationManager notificationManager =
                            (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    notificationManager.cancel(NOTIFICATION_ID);
                }
            }
        });

        mSetAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long alertTime = new GregorianCalendar().getTimeInMillis() + 5 * 1000;

                Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                alarmManager.set(AlarmManager.RTC_WAKEUP, alertTime,
                        PendingIntent.getBroadcast(MainActivity.this,
                                REQUEST_CODE, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT));
            }
        });
    }

}
