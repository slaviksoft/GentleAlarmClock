package ru.yomu.slaviksoft.gentlealarmclock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;

/**
 * Created by Slavik on 27.09.2015.
 * Database helper for alarm items
 */
public class DbHelper extends SQLiteOpenHelper{

    private String TAG = getClass().getName();

    private static final int VERSION = 7;
    private static final String DBNAME = "alarms";
    private static final String TABLE_ALARMS = "alarms";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_WEEK_DAYS_1 = "day1";
    public static final String KEY_WEEK_DAYS_2 = "day2";
    public static final String KEY_WEEK_DAYS_3 = "day3";
    public static final String KEY_WEEK_DAYS_4 = "day4";
    public static final String KEY_WEEK_DAYS_5 = "day5";
    public static final String KEY_WEEK_DAYS_6 = "day6";
    public static final String KEY_WEEK_DAYS_7 = "day7";
    public static final String KEY_TIME_HOUR     = "hour";
    public static final String KEY_TIME_MINUTES  = "minutes";

    public DbHelper(Context context){
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "db create");
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);
        onCreate(db);
    }

    private Cursor fetchAlarms() {

        SQLiteDatabase db = getReadableDatabase();

        String[] Columns = new String[]{
                KEY_ID,
                KEY_NAME,
                KEY_TIME_HOUR,
                KEY_TIME_MINUTES,
                KEY_WEEK_DAYS_1,
                KEY_WEEK_DAYS_2,
                KEY_WEEK_DAYS_3,
                KEY_WEEK_DAYS_4,
                KEY_WEEK_DAYS_5,
                KEY_WEEK_DAYS_6,
                KEY_WEEK_DAYS_7
        };

        Cursor cursor = db.query(TABLE_ALARMS, Columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public ArrayList<AlarmItem> fetchAlarmItems(){

        ArrayList<AlarmItem> list = new ArrayList();
        Cursor cursor = fetchAlarms();

        if (cursor.moveToFirst()) {
            do {
                AlarmItem item = new AlarmItem(cursor);
                list.add(item);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;

    }

    public void addAlarm(AlarmItem item){

        ContentValues values = item.getContent();

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_ALARMS, null, values);
        db.close();

    }

    public void updateAlarm(AlarmItem item){

        ContentValues values = item.getContent();

        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_ALARMS, values, KEY_ID+"="+item.id, null);
        db.close();

    }

    public void deleteAlarm(AlarmItem item){

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_ALARMS, KEY_ID + "=" + item.id, null);

    }


    private static final String DATABASE_CREATE =
            "CREATE TABLE if not exists " +
            TABLE_ALARMS + " (" +
                KEY_ID + " integer PRIMARY KEY autoincrement," +
                KEY_NAME +" string, "+
                KEY_TIME_HOUR + " integer,"+
                KEY_TIME_MINUTES + " integer,"+
                KEY_WEEK_DAYS_1 +" integer, "+
                KEY_WEEK_DAYS_2 +" integer, "+
                KEY_WEEK_DAYS_3 +" integer, "+
                KEY_WEEK_DAYS_4 +" integer, "+
                KEY_WEEK_DAYS_5 +" integer, "+
                KEY_WEEK_DAYS_6 +" integer, "+
                KEY_WEEK_DAYS_7 +" integer"+
                ");";



}
