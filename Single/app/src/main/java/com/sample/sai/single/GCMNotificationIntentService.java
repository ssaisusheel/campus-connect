package com.sample.sai.single;

/**
 * Created by chimmu123 on 1/5/2015.
 */
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMNotificationIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    //final static String GROUP_KEY = "group_key_emails";
    static int notify_id=0;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    Intent intent;
    final GCMNotificationIntentService tr=this;
        String domain="",subject="";
    public GCMNotificationIntentService() {
        super("GcmIntentService");
    }

    public static final String TAG = "GCMNotificationIntentService";

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR
                    .equals(messageType)) {
                sendNotification("Send error: " + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED
                    .equals(messageType)) {
                sendNotification("Deleted messages on server: "
                        + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE
                    .equals(messageType)) {

                for (int i = 0; i < 3; i++) {
                    Log.i(TAG,
                            "Working... " + (i + 1) + "/5 @ "
                                    + SystemClock.elapsedRealtime());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }

                }
                Log.i(TAG, "Completed work @ " + SystemClock.elapsedRealtime());

                //   sendNotification("Message Received from Google GCM Server: "
                //     + extras.get(config.MESSAGE_KEY));
                domain=(String)extras.get(config.DOMAIN);
                Log.d("testing:",domain);
                sendNotification((String) extras.get(config.MESSAGE_KEY));
                Log.i(TAG, "Received: " + extras.toString());
            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(String msg) {

        Log.d(TAG, "Preparing to send notification...: " + msg);
        mNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Context cw= getApplicationContext();
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(domain.equals("a1"))
        {
            subject="Class Notification";

        }
        else if(domain.equals("a2"))
        {
            subject="Dept Notification";
        }
        else if(domain.equals("a3"))
        {
            subject="College Notification";
        }
        //notify_id=notify_id+1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Campus Connect")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(subject))
                .setContentText(subject)
                .setSound(alarmSound);
        // notification.defaults |= Notification.DEFAULT_LIGHTS;

        /*Intent intent=new Intent(tr,Activity_2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("ID",msg);*/
        if(domain.equals("a1"))
        {
           // subject="Class Notification";
            intent=new Intent(tr,Activity_2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("ID",msg);
        }
        else if(domain.equals("a2"))
        {
           // subject="Dept Notification";
            intent=new Intent(tr,Activity_21.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("ID",msg);
        }
        else if(domain.equals("a3"))
        {
           // subject="College Notification";
            intent=new Intent(tr,Activity_22.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("ID",msg);
        }

       if(domain.equals("a1"))
        {

            Databasehelper db = new Databasehelper(cw);

            db.insertMsg(msg);
        }
        else if(domain.equals("a2"))
        {
            Databasehelper1 db1 = new Databasehelper1(cw);
            db1.insertMsg(msg);
        }
        else if(domain.equals("a3"))
        {
            Databasehelper2 db2 = new Databasehelper2(cw);
            db2.insertMsg(msg);
        }
       //Databasehelper db = new Databasehelper(cw);
        //db.openToWrite();
       //db.insertMsg(msg);
       // db.close();

        android.app.PendingIntent pint=android.app.PendingIntent.getActivity(tr,notify_id++,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pint);
        NotificationManager mg=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //final static String GROUP_KEY_EMAILS = "group_key_emails"
        Notification noti=mBuilder.build();
        // final static String GROUP_KEY_EMAILS = "group_key_emails"
        noti.flags|=Notification.FLAG_AUTO_CANCEL;
        noti.defaults|= Notification.DEFAULT_LIGHTS;
        //  notification.defaults |= Notification.DEFAULT_LIGHTS;

        //db.getAllBooks();
        mg.notify(notify_id-1,noti);
        //  mBuilder.setContentIntent(contentIntent);
        //mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());

        Log.d(TAG, "Notification sent successfully.");
    }
}