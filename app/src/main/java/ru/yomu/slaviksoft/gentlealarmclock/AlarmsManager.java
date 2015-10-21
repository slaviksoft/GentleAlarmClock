package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Slavik on 27.09.2015.
 */
public class AlarmsManager{

    private DbHelper db;
    private AlarmsArrayAdapter alarmsArrayAdapter;

    public AlarmsManager(Context context){
        db = new DbHelper(context);
        ArrayList<AlarmItem> list = db.fetchAlarmItems();
        alarmsArrayAdapter = new AlarmsArrayAdapter(context, R.layout.alarms_list_item, list);
    }



    public AlarmsArrayAdapter getCursorAdapter() {
        return alarmsArrayAdapter;
    }

    public void addAlarm(){

        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm");
        String formattedDate = df.format(rightNow.getTime());

        AlarmItem item = new AlarmItem("Alarm", formattedDate, 1, 1, 1, 0, 0, 1, 1);

        db.addAlarm(item);

        alarmsArrayAdapter.add(item);
        alarmsArrayAdapter.notifyDataSetChanged();


    }

    public void deleteAlarm(int position){

        AlarmItem item = alarmsArrayAdapter.getItem(position);

        db.deleteAlarm(item);
        alarmsArrayAdapter.remove(item);
        alarmsArrayAdapter.notifyDataSetChanged();

    }

}
