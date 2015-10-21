package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Slavik on 21.10.2015.
 */
public class AlarmItem {

    public long id;
    public String name;
    public String time;
    public boolean day1;
    public boolean day2;
    public boolean day3;
    public boolean day4;
    public boolean day5;
    public boolean day6;
    public boolean day7;

    public AlarmItem(Cursor cursor){
        id   = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.KEY_ID));
        name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.KEY_NAME));
        time = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.KEY_TIME));
        day1 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_1)) == 1;
        day2 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_2)) == 1;
        day3 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_3)) == 1;
        day4 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_4)) == 1;
        day5 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_5)) == 1;
        day6 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_6)) == 1;
        day7 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_7)) == 1;
    }

    public AlarmItem(String name, String time, int day1, int day2, int day3, int day4, int day5, int day6, int day7){
        this.name = name;
        this.time = time;
        this.day1 = day1 == 1;
        this.day2 = day2 == 1;
        this.day3 = day3 == 1;
        this.day4 = day4 == 1;
        this.day5 = day5 == 1;
        this.day6 = day6 == 1;
        this.day7 = day7 == 1;
    }


    public ContentValues getContent(){
        ContentValues values = new ContentValues();

        values.put(DbHelper.KEY_NAME, name);
        values.put(DbHelper.KEY_TIME, time);
        values.put(DbHelper.KEY_WEEK_DAYS_1, day1);
        values.put(DbHelper.KEY_WEEK_DAYS_2, day2);
        values.put(DbHelper.KEY_WEEK_DAYS_3, day3);
        values.put(DbHelper.KEY_WEEK_DAYS_4, day4);
        values.put(DbHelper.KEY_WEEK_DAYS_5, day5);
        values.put(DbHelper.KEY_WEEK_DAYS_6, day6);
        values.put(DbHelper.KEY_WEEK_DAYS_7, day7);

        return values;
    }

}
