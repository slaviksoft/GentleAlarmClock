package ru.yomu.slaviksoft.gentlealarmclock.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;
import ru.yomu.slaviksoft.gentlealarmclock.AlarmsManager;
import ru.yomu.slaviksoft.gentlealarmclock.activities.AlarmActivity;

public class AlarmEventReceiver extends BroadcastReceiver {

    public AlarmEventReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AlarmItem alarm = intent.getParcelableExtra("ALARM");

        Log.d("DEBUG_EVENT", "alarm = "+alarm.name);

        AlarmsServiceManager alarmsServiceManager = new AlarmsServiceManager(context);
        alarmsServiceManager.update(alarm);

        if (!alarm.isRepeated()){
            alarm.enabled = false;
            AlarmsManager alarmsManager = new AlarmsManager(context);
            alarmsManager.updateAlarm(alarm);
        }

        Intent i = new Intent(context, AlarmActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("ALARM", alarm);//intent.getStringExtra("INFO"));
        context.startActivity(i);
    }
}
