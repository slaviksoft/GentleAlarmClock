package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.yomu.slaviksoft.gentlealarmclock.database.AlarmsArrayAdapter;
import ru.yomu.slaviksoft.gentlealarmclock.database.DbHelper;
import ru.yomu.slaviksoft.gentlealarmclock.services.AlarmsServiceManager;

/**
 * Created by Slavik on 27.09.2015.
 * Manager for db
 */
public class AlarmsManager{

    private DbHelper db;
    private AlarmsArrayAdapter alarmsArrayAdapter;
    private AlarmsServiceManager alarmsServiceManager;
    private Context context;

    public AlarmsManager(Context context){
        db = new DbHelper(context);
        ArrayList<AlarmItem> list = db.fetchAlarmItems();
        alarmsArrayAdapter = new AlarmsArrayAdapter(context, R.layout.alarms_list_item, list);
        alarmsArrayAdapter.setNotifyOnChange(true);
        this.context = context;

        alarmsServiceManager = new AlarmsServiceManager(context);
    }

    public AlarmItem getAlarmItem(int position){
        return alarmsArrayAdapter.getItem(position);
    }

    public AlarmsArrayAdapter getCursorAdapter() {
        return alarmsArrayAdapter;
    }

    public void addAlarm(AlarmItem alarmItem){

        db.addAlarm(alarmItem);
        alarmsArrayAdapter.add(alarmItem);
        alarmsServiceManager.add(alarmItem);

    }

    public void updateAlarm(AlarmItem alarmItem){
        db.updateAlarm(alarmItem);
    }

    public void updateAlarm(int position, AlarmItem alarmItem){

        updateAlarm(alarmItem);

        AlarmItem oldItem = alarmsArrayAdapter.getItem(position);
        alarmsArrayAdapter.remove(oldItem);
        alarmsArrayAdapter.insert(alarmItem, position);

        alarmsServiceManager.update(alarmItem);

    }

    public void deleteAlarm(int position){

        AlarmItem item = alarmsArrayAdapter.getItem(position);

        db.deleteAlarm(item);

        alarmsArrayAdapter.remove(item);

        alarmsServiceManager.closeAlarm(item);

    }

    public AlarmItem getNewAlarmItem(){

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 1);
        return new AlarmItem("New alarm ", 1, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), 1, 1, 1, 1, 1, 1, 1);

    }

    public List<AlarmItem> fetchAlarmsItems(){

        return db.fetchAlarmItems();

    }



}
