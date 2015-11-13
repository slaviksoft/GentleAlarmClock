package ru.yomu.slaviksoft.gentlealarmclock.services;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;

/**
 * Created by Slavik on 31.08.2015.
 */
public class AlarmsServiceManager {

    AlarmManager alarmManager;
    Context context;
    Class<?> cls = AlarmEventReceiver.class;

    public AlarmsServiceManager(Context context){
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void addAlarm(Calendar next, AlarmItem alarm){

        if (next == null) {
            closeAlarm(alarm);
            return;
        }

        Intent i = new Intent(context, cls);
        i.putExtra("START", next.getTime().toString());
        i.putExtra("INFO", alarm.name);
        i.putExtra("ALARM", alarm);

        PendingIntent pendingIntent = getPendingIntent(alarm.getId(), i);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);

        if (Build.VERSION_CODES.LOLLIPOP == Build.VERSION.SDK_INT){
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, next.getTimeInMillis(), pendingIntent);
            }
        else{
            alarmManager.set(AlarmManager.RTC_WAKEUP, next.getTimeInMillis(), pendingIntent);
            }
        Log.d("DEBUG", "set "+alarm.name+" = "+next.getTime());
    }


    public void add(AlarmItem alarm){

        Calendar next = alarm.getNextCalendar();
        addAlarm(next, alarm);

    }

    public void update(AlarmItem alarm){

        Calendar next = alarm.getNextCalendar();
        addAlarm(next, alarm);

    }


    public void closeAlarm(AlarmItem alarm){

        Intent i = new Intent(context, cls);

        PendingIntent pendingIntent = getPendingIntent(alarm.getId(), i);

        alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        Log.d("DEBUG", "cancel");
    }

    private PendingIntent getPendingIntent(int id, Intent i){
        return PendingIntent.getBroadcast(context, id, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
