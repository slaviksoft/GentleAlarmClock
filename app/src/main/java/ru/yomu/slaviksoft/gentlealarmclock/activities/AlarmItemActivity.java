package ru.yomu.slaviksoft.gentlealarmclock.activities;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;
import ru.yomu.slaviksoft.gentlealarmclock.R;

public class AlarmItemActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private final String TAG = getClass().getSimpleName();
    private AlarmItem alarmItem;

    private CheckBox checkBoxEnabled;
    private EditText editTextName;

    private CheckBox checkBoxDay1;
    private CheckBox checkBoxDay2;
    private CheckBox checkBoxDay3;
    private CheckBox checkBoxDay4;
    private CheckBox checkBoxDay5;
    private CheckBox checkBoxDay6;
    private CheckBox checkBoxDay7;

    private TextView textViewTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmItem = getIntent().getParcelableExtra("item");

        checkBoxEnabled = (CheckBox) findViewById(R.id.checkBoxEnabled);
        editTextName = (EditText) findViewById(R.id.editTextName);
        checkBoxDay1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBoxDay2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBoxDay3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBoxDay4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBoxDay5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBoxDay6 = (CheckBox) findViewById(R.id.checkBox6);
        checkBoxDay7 = (CheckBox) findViewById(R.id.checkBox7);
        textViewTime = (TextView) findViewById(R.id.textViewTime);

        readDataFromItem();

        Button buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeDataToItem();
                Intent i = getIntent().putExtra("item", alarmItem);
                setResult(RESULT_OK, i);
                finish();
            }
        });

        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("hour", alarmItem.time_hour);
                bundle.putInt("minute", alarmItem.time_minute);

                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setArguments(bundle);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });

    }

    private void readDataFromItem(){

        checkBoxEnabled.setChecked(alarmItem.enabled);
        editTextName.setText(alarmItem.name);
        checkBoxDay1.setChecked(alarmItem.day1);
        checkBoxDay2.setChecked(alarmItem.day2);
        checkBoxDay3.setChecked(alarmItem.day3);
        checkBoxDay4.setChecked(alarmItem.day4);
        checkBoxDay5.setChecked(alarmItem.day5);
        checkBoxDay6.setChecked(alarmItem.day6);
        checkBoxDay7.setChecked(alarmItem.day7);

        updateViewTime();

    }

    private void updateViewTime(){

        String strTime = AlarmItem.getTimeString(getBaseContext(), alarmItem);
        textViewTime.setText(strTime);

    }

    private void writeDataToItem(){

        alarmItem.name = editTextName.getText().toString();
        alarmItem.enabled = checkBoxEnabled.isChecked();
        alarmItem.day1 = checkBoxDay1.isChecked();
        alarmItem.day2 = checkBoxDay2.isChecked();
        alarmItem.day3 = checkBoxDay3.isChecked();
        alarmItem.day4 = checkBoxDay4.isChecked();
        alarmItem.day5 = checkBoxDay5.isChecked();
        alarmItem.day6 = checkBoxDay6.isChecked();
        alarmItem.day7 = checkBoxDay7.isChecked();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        alarmItem.time_hour = hourOfDay;
        alarmItem.time_minute = minute;

        updateViewTime();
    }

    public static class TimePickerFragment extends DialogFragment{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int hour = getArguments().getInt("hour");
            int minute = getArguments().getInt("minute");
            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

    }




}
