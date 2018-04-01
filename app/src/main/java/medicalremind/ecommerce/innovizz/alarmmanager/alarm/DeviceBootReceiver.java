package medicalremind.ecommerce.innovizz.alarmmanager.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hassan M Ashraful on 3/29/2018.
 * Jr Software Developer
 * Innovizz Technology
 */
public class DeviceBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            /*ArrayList<Calendar> calendars = new ArrayList<>();

            *//* Set the alarm *//*
            Calendar calendarOne = Calendar.getInstance();
            calendarOne.setTimeInMillis(System.currentTimeMillis());
            calendarOne.set(Calendar.HOUR_OF_DAY, 16);
            calendarOne.set(Calendar.MINUTE, 8);

            Calendar calendarTwo = Calendar.getInstance();
            calendarTwo.setTimeInMillis(System.currentTimeMillis());
            calendarTwo.set(Calendar.HOUR_OF_DAY, 16);
            calendarTwo.set(Calendar.MINUTE, 10);

            calendars.add(calendarOne); calendars.add(calendarTwo);*/


            long []times = new long[2];
            ArrayList<String> dates = new ArrayList<>();
            String myDate = "2018/04/01 14:24:00";  String myDateTwo = "2018/04/01 14:26:00";
            dates.add(myDate); dates.add(myDateTwo);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            for (int i = 0; i<2; i++){
                Date date = null;
                try {
                    date = sdf.parse(dates.get(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                assert date != null;
                long millis = date.getTime(); times[i] = millis;
            }

            /* Setting the alarm here */
            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            for (int f = 0; f<2; f++){
                Intent alarmIntent = new Intent(context, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, f, alarmIntent, 0);
                manager.set(AlarmManager.RTC_WAKEUP, times[f], pendingIntent);
            }



            int interval = 8000;


            Toast.makeText(context, "Alarm Set", Toast.LENGTH_LONG).show();
        }
    }
}
