package ru.yomu.slaviksoft.gentlealarmclock.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;
import ru.yomu.slaviksoft.gentlealarmclock.R;

/**
 * Created by Slavik on 21.10.2015.
 * manage alarms records in list
 */
public class AlarmsArrayAdapter extends ArrayAdapter<AlarmItem> {

    private LayoutInflater inflater;

    public AlarmsArrayAdapter(Context context, int resource, List<AlarmItem> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.alarms_list_item, parent, false);
        }

        AlarmItem item = getItem(position);

        TextView tvName = (TextView) convertView.findViewById(R.id.textViewAlarmName);
        TextView tvTime = (TextView) convertView.findViewById(R.id.textViewAlarmTime);
        TextView tvDay1 = (TextView) convertView.findViewById(R.id.textViewDay1);
        TextView tvDay2 = (TextView) convertView.findViewById(R.id.textViewDay2);
        TextView tvDay3 = (TextView) convertView.findViewById(R.id.textViewDay3);
        TextView tvDay4 = (TextView) convertView.findViewById(R.id.textViewDay4);
        TextView tvDay5 = (TextView) convertView.findViewById(R.id.textViewDay5);
        TextView tvDay6 = (TextView) convertView.findViewById(R.id.textViewDay6);
        TextView tvDay7 = (TextView) convertView.findViewById(R.id.textViewDay7);

//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, item.time_hour);
//        c.set(Calendar.MINUTE, item.time_minute);
//
//        java.text.DateFormat df = DateFormat.getTimeFormat(getContext());
//        df.setCalendar(c);
//        String strTime = df.format(c.getTime());

        String strTime = AlarmItem.getTimeString(getContext(), item);

        tvName.setText(item.name);
        tvTime.setText(strTime);
        tvDay1.setEnabled(item.day1);
        tvDay2.setEnabled(item.day2);
        tvDay3.setEnabled(item.day3);
        tvDay4.setEnabled(item.day4);
        tvDay5.setEnabled(item.day5);
        tvDay6.setEnabled(item.day6);
        tvDay7.setEnabled(item.day7);

        return convertView;

    }
}
