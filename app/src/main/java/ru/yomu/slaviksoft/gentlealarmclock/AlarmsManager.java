package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Slavik on 27.09.2015.
 * Manager for db
 */
public class AlarmsManager{

    private DbHelper db;
    private AlarmsArrayAdapter alarmsArrayAdapter;
    private Context context;

    public AlarmsManager(Context context){
        db = new DbHelper(context);
        ArrayList<AlarmItem> list = db.fetchAlarmItems();
        alarmsArrayAdapter = new AlarmsArrayAdapter(context, R.layout.alarms_list_item, list);
        alarmsArrayAdapter.setNotifyOnChange(true);
        this.context = context;
    }

    public AlarmItem getAlarmItem(int position){
        return alarmsArrayAdapter.getItem(position);
    }

    public AlarmsArrayAdapter getCursorAdapter() {
        return alarmsArrayAdapter;
    }

    public void addAlarm(AlarmItem alarmItem){

        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm");
//        String formattedDate = df.format(rightNow.getTime());

        db.addAlarm(alarmItem);

        alarmsArrayAdapter.add(alarmItem);

    }

    public void updateAlarm(int position, AlarmItem alarmItem){

        db.updateAlarm(alarmItem);

        AlarmItem oldItem = alarmsArrayAdapter.getItem(position);
        alarmsArrayAdapter.remove(oldItem);
        alarmsArrayAdapter.insert(alarmItem, position);

    }

    public void deleteAlarm(int position){

        AlarmItem item = alarmsArrayAdapter.getItem(position);

        db.deleteAlarm(item);
        alarmsArrayAdapter.remove(item);

    }

    public AlarmItem getNewAlarmItem(){
        return new AlarmItem("New alarm", "18:00", 1, 1, 1, 1, 1, 1, 1);
    }

}
