package medicalremind.ecommerce.innovizz.alarmmanager;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by YTC on 3/29/2018.
 */

public class NotificationScheduler {

    //public static final int DAILY_REMINDER_REQUEST_CODE=100;
    public static final String TAG="NotificationScheduler";
    private String showME = "";

    public static void setReminder(Context context, Class<?> cls, ArrayList<PendingIntent> intentArray, ArrayList<Calendar> calendars, String title)
    {
        Calendar calendar = Calendar.getInstance();

        /*Calendar setcalendar = Calendar.getInstance();
        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);*/

        // cancel already scheduled reminders

        for (int i = 0; i<intentArray.size(); i++){
            cancelReminder(context,cls, i);

            if(calendars.get(i).before(calendar))
                calendars.get(i).add(Calendar.DATE,1);

            // Enable a receiver

            ComponentName receiver = new ComponentName(context, cls);
            PackageManager pm = context.getPackageManager();

            pm.setComponentEnabledSetting(receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);


            Intent intent1 = new Intent(context, cls);
            PendingIntent pendingIntent = intentArray.get(i);
            AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, calendars.get(i).getTimeInMillis(), pendingIntent);
            /*PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendars.get(i).getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);*/
        }



    }

    public static void cancelReminder(Context context,Class<?> cls, int key)
    {
        // Disable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, key, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void showNotification(Context context,Class<?> cls, String title,int key, String content)
    {

        SharedPreferences prefs = context.getSharedPreferences("title_for_notify", MODE_PRIVATE);
        String restoredText = prefs.getString("title", null); String name = title;
        if (restoredText != null) {
            name = prefs.getString("title", "...");//"No name defined" is the default value.

        }

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent notificationIntent = new Intent(context, cls);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(cls);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(key, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle(name)
                .setContentText(content)
                .setAutoCancel(true)
                .setSound(alarmSound)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(pendingIntent).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(key, notification);

    }



}
