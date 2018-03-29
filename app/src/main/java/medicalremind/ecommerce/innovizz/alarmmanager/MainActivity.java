package medicalremind.ecommerce.innovizz.alarmmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import medicalremind.ecommerce.innovizz.alarmmanager.alarm.AlarmReceiver;

public class MainActivity extends AppCompatActivity {

    //private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        ArrayList<Calendar> calendars = new ArrayList<>();
        Calendar calenderOne = Calendar.getInstance();
        calenderOne.set(Calendar.HOUR_OF_DAY, 14);
        calenderOne.set(Calendar.MINUTE, 1);
        calenderOne.set(Calendar.SECOND, 0);

        Calendar calenderTwo = Calendar.getInstance();
        calenderTwo.set(Calendar.HOUR_OF_DAY, 14);
        calenderTwo.set(Calendar.MINUTE, 3);
        calenderTwo.set(Calendar.SECOND, 0);

        calendars.add(calenderOne); calendars.add(calenderTwo);

        for(int i = 0; i < 2; ++i)
        {
            Intent intent = new Intent(getApplicationContext(), MyReceiver.class);
// Loop counter `i` is used as a `requestCode`
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, intent, 0);
// Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
           // mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + 60000 * i, pendingIntent);

            intentArray.add(pendingIntent);
        }

        NotificationScheduler.setReminder(MainActivity.this, MyReceiver.class, intentArray, calendars, "");*/

        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

        //ArrayList<Calendar> calendars = new ArrayList<>();

        /* Retrieve a PendingIntent that will perform a broadcast */
        /*Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);*/

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;


        long []times = new long[2];
        ArrayList<String> dates = new ArrayList<>();
        String myDate = "2018/03/29 17:05:00";  String myDateTwo = "2018/03/29 17:07:00";
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


        /* Set the alarm */
        Calendar calendarOne = Calendar.getInstance();
        calendarOne.setTimeInMillis(System.currentTimeMillis());
        calendarOne.set(Calendar.HOUR_OF_DAY, 16);
        calendarOne.set(Calendar.MINUTE, 14);

        Calendar calendarTwo = Calendar.getInstance();
        calendarTwo.setTimeInMillis(System.currentTimeMillis());
        calendarTwo.set(Calendar.HOUR_OF_DAY, 16);
        calendarTwo.set(Calendar.MINUTE, 16);

       // calendars.add(calendarOne); calendars.add(calendarTwo);

        /*for(int i = 0; i < 2; ++i)
        {
            Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
// Loop counter `i` is used as a `requestCode`
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, i, alarmIntent, 0);
// Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
            manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    calendars.get(i).getTimeInMillis(),
                    pendingIntent);

            intentArray.add(pendingIntent);
        }*/

        AlarmManager[] alarmManager=new AlarmManager[24];
        intentArray = new ArrayList<PendingIntent>(); PendingIntent pi;
        for(int f=0;f<2;f++){
            Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
            pi=PendingIntent.getBroadcast(MainActivity.this, f,intent, 0);

            alarmManager[f] = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager[f].set(AlarmManager.RTC_WAKEUP, times[f], pi);

            intentArray.add(pi);

        }



        /* Repeating on every 20 minutes interval */
       // manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    private void cancelAlarms() {
        /*if (intentArray.size() > 0) {
            for (int i = 0; i < intentArray.size(); i++) {
                alarmmanager.cancel(intentArray.get(i));
            }
            intentArray.clear();
        }*/
    }
}
