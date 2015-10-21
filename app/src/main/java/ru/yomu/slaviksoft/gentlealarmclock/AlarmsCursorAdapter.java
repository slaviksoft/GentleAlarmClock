package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Slavik on 20.10.2015.
 */
public class AlarmsCursorAdapter extends CursorAdapter{

    public AlarmsCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.alarms_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView tvName = (TextView) view.findViewById(R.id.textViewAlarmName);
        TextView tvTime = (TextView) view.findViewById(R.id.textViewAlarmTime);
        TextView tvDay1 = (TextView) view.findViewById(R.id.textViewDay1);
        TextView tvDay2 = (TextView) view.findViewById(R.id.textViewDay2);
        TextView tvDay3 = (TextView) view.findViewById(R.id.textViewDay3);
        TextView tvDay4 = (TextView) view.findViewById(R.id.textViewDay4);
        TextView tvDay5 = (TextView) view.findViewById(R.id.textViewDay5);
        TextView tvDay6 = (TextView) view.findViewById(R.id.textViewDay6);
        TextView tvDay7 = (TextView) view.findViewById(R.id.textViewDay7);

        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.KEY_NAME));
        String time = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.KEY_TIME));
        boolean day1 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_1))==1;
        boolean day2 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_2))==1;
        boolean day3 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_3))==1;
        boolean day4 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_4))==1;
        boolean day5 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_5))==1;
        boolean day6 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_6))==1;
        boolean day7 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_7))==1;


        // Populate fields with extracted properties
        tvName.setText(name);
        tvTime.setText(time);

        tvDay1.setEnabled(day1);
        tvDay2.setEnabled(day2);
        tvDay3.setEnabled(day3);
        tvDay4.setEnabled(day4);
        tvDay5.setEnabled(day5);
        tvDay6.setEnabled(day6);
        tvDay7.setEnabled(day7);


    }
}
