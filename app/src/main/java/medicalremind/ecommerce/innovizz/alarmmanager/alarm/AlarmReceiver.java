package medicalremind.ecommerce.innovizz.alarmmanager.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import medicalremind.ecommerce.innovizz.alarmmanager.MainActivity;
import medicalremind.ecommerce.innovizz.alarmmanager.R;
import medicalremind.ecommerce.innovizz.alarmmanager.TestActivity;
import medicalremind.ecommerce.innovizz.alarmmanager.service.RingtonePlayingService;

/**
 * Created by Hassan M Ashraful on 3/29/2018.
 * Jr Software Developer
 * Innovizz Technology
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "I'm running", Toast.LENGTH_LONG).show();
        Log.v("alarm$$$$  ","1"+ System.currentTimeMillis());



        Intent startIntent = new Intent(context, RingtonePlayingService.class);
        //ringtoneServiceStartIntent.putExtra("ringtone-uri", alarmSound);
        context.startService(startIntent);

        String s=intent.getStringExtra("alarm");

        Intent notificationIntent = new Intent(context, TestActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtra("alarm", s);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(TestActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(s)
                .setContentText("Hello")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }
}
