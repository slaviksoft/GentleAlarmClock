package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ru.yomu.slaviksoft.gentlealarmclock.database.DbHelper;

/**
 * Created by Slavik on 21.10.2015.
 * class of alarm item
 */
public class AlarmItem implements Parcelable {

    public long id;
    public String name;
    public boolean enabled;
    public int time_hour;
    public int time_minute;
    public boolean day1;
    public boolean day2;
    public boolean day3;
    public boolean day4;
    public boolean day5;
    public boolean day6;
    public boolean day7;

    public AlarmItem(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndexOrThrow(DbHelper.KEY_ID));
        enabled = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_ENABLED)) == 1;
        name = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.KEY_NAME));
        time_hour = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_TIME_HOUR));
        time_minute = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_TIME_MINUTES));
        day1 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_1)) == 1;
        day2 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_2)) == 1;
        day3 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_3)) == 1;
        day4 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_4)) == 1;
        day5 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_5)) == 1;
        day6 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_6)) == 1;
        day7 = cursor.getInt(cursor.getColumnIndexOrThrow(DbHelper.KEY_WEEK_DAYS_7)) == 1;
    }

    public AlarmItem(String name, int enabled, int time_hour, int time_minutes, int day1, int day2, int day3, int day4, int day5, int day6, int day7) {
        this.name = name;
        this.enabled = enabled == 1;
        this.time_hour = time_hour;
        this.time_minute = time_minutes;
        this.day1 = day1 == 1;
        this.day2 = day2 == 1;
        this.day3 = day3 == 1;
        this.day4 = day4 == 1;
        this.day5 = day5 == 1;
        this.day6 = day6 == 1;
        this.day7 = day7 == 1;
    }

    protected AlarmItem(Parcel in) {
        id = in.readLong();
        enabled = in.readByte() != 0;
        name = in.readString();
        time_hour = in.readInt();
        time_minute = in.readInt();
        day1 = in.readByte() != 0;
        day2 = in.readByte() != 0;
        day3 = in.readByte() != 0;
        day4 = in.readByte() != 0;
        day5 = in.readByte() != 0;
        day6 = in.readByte() != 0;
        day7 = in.readByte() != 0;
    }

    public static final Creator<AlarmItem> CREATOR = new Creator<AlarmItem>() {
        @Override
        public AlarmItem createFromParcel(Parcel in) {
            return new AlarmItem(in);
        }

        @Override
        public AlarmItem[] newArray(int size) {
            return new AlarmItem[size];
        }
    };

    public int getId() {
        return (int) id;
    }

    public ContentValues getContent() {
        ContentValues values = new ContentValues();

        values.put(DbHelper.KEY_ENABLED, enabled);
        values.put(DbHelper.KEY_NAME, name);
        values.put(DbHelper.KEY_TIME_HOUR, time_hour);
        values.put(DbHelper.KEY_TIME_MINUTES, time_minute);
        values.put(DbHelper.KEY_WEEK_DAYS_1, day1);
        values.put(DbHelper.KEY_WEEK_DAYS_2, day2);
        values.put(DbHelper.KEY_WEEK_DAYS_3, day3);
        values.put(DbHelper.KEY_WEEK_DAYS_4, day4);
        values.put(DbHelper.KEY_WEEK_DAYS_5, day5);
        values.put(DbHelper.KEY_WEEK_DAYS_6, day6);
        values.put(DbHelper.KEY_WEEK_DAYS_7, day7);

        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeInt(enabled ? 1 : 0);
        dest.writeString(name);
        dest.writeInt(time_hour);
        dest.writeInt(time_minute);
        dest.writeInt(day1 ? 1 : 0);
        dest.writeInt(day2 ? 1 : 0);
        dest.writeInt(day3 ? 1 : 0);
        dest.writeInt(day4 ? 1 : 0);
        dest.writeInt(day5 ? 1 : 0);
        dest.writeInt(day6 ? 1 : 0);
        dest.writeInt(day7 ? 1 : 0);

    }

    public static String getTimeString(Context context, AlarmItem item){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, item.time_hour);
        c.set(Calendar.MINUTE, item.time_minute);
        DateFormat df = android.text.format.DateFormat.getTimeFormat(context);
        return df.format(c.getTime());

    }

    public boolean isRepeated(){
        return day1 || day2 || day3 || day4 || day5 || day6 || day7;
    }

    public Calendar getNextCalendar(){

        if (!enabled) return null;

        boolean[] days = new boolean[7];
        days[0] = day7; //su
        days[1] = day1; //mo
        days[2] = day2; //tu
        days[3] = day3; //wed
        days[4] = day4; //th
        days[5] = day5; //fr
        days[6] = day6; //sa

        DateFormat df = SimpleDateFormat.getInstance();

        Calendar now = Calendar.getInstance();
        int nowDayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int nowMinute = now.get(Calendar.MINUTE);

        Calendar next = Calendar.getInstance();
        next.set(Calendar.HOUR_OF_DAY, time_hour);
        next.set(Calendar.MINUTE, time_minute);
        next.set(Calendar.SECOND, 0);

        boolean calendarSet = false;

        if (isRepeated()){

            for (int day=Calendar.SUNDAY; day <= Calendar.SATURDAY; day++){
                if (
                        days[day-1] &&
                                (
                                        (day > nowDayOfWeek)
                                                ||
                                                (day == nowDayOfWeek && nowHour < time_hour)
                                                ||
                                                (day == nowDayOfWeek && nowHour == time_hour && nowMinute < time_minute)
                                )
                        )
                {
                    next.set(Calendar.DAY_OF_WEEK, day);
                    calendarSet = true;
                    break;
                }
            }

            if(!calendarSet){
                for (int day=Calendar.SUNDAY; day <= Calendar.SATURDAY; day++){
                    if(days[day-1] && day <= nowDayOfWeek){
                        next.set(Calendar.DAY_OF_WEEK, day);
                        next.add(Calendar.WEEK_OF_YEAR, 1);
                        calendarSet = true;
                        break;
                    }
                }
            }

        }else{
            if(
                    (nowHour < time_hour) ||
                            ( time_hour == nowHour && nowMinute < time_minute)
                    )
            {
                calendarSet = true;
            }
        }

        if(calendarSet)
            return next;
        else
            return null;

    }

}
