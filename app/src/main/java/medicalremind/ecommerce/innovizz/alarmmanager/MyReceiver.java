package medicalremind.ecommerce.innovizz.alarmmanager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by YTC on 3/29/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                //LocalData localData = new LocalData(context);


                //NotificationScheduler.setReminder(context, MyReceiver.class, 12, 0, "");
                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        ArrayList<Calendar> calendars = new ArrayList<>();
        Calendar calenderOne = Calendar.getInstance();
        calenderOne.set(Calendar.HOUR_OF_DAY, 13);
        calenderOne.set(Calendar.MINUTE, 50);
        calenderOne.set(Calendar.SECOND, 0);

        Calendar calenderTwo = Calendar.getInstance();
        calenderTwo.set(Calendar.HOUR_OF_DAY, 13);
        calenderTwo.set(Calendar.MINUTE, 52);
        calenderTwo.set(Calendar.SECOND, 0);

        calendars.add(calenderOne); calendars.add(calenderTwo);

        for(int i = 0; i < 2; ++i)
        {
// Loop counter `i` is used as a `requestCode`
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, i, intent, 0);
// Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
            // mgrAlarm.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,SystemClock.elapsedRealtime() + 60000 * i, pendingIntent);

            intentArray.add(pendingIntent);
            //Trigger the notification
            NotificationScheduler.showNotification(context, MainActivity.class,
                    "Read today news", i, ".......");
        }

    }

}
