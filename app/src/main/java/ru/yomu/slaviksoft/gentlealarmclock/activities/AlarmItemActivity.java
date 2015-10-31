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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmItem = getIntent().getParcelableExtra("item");
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

    }

    private void readDataFromItem(){

        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        editTextName.setText(alarmItem.name);

        CheckBox checkBoxDays;
        checkBoxDays = (CheckBox) findViewById(R.id.checkBox1);
        checkBoxDays.setChecked(alarmItem.day1);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox2);
        checkBoxDays.setChecked(alarmItem.day2);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox3);
        checkBoxDays.setChecked(alarmItem.day3);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox4);
        checkBoxDays.setChecked(alarmItem.day4);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox5);
        checkBoxDays.setChecked(alarmItem.day5);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox6);
        checkBoxDays.setChecked(alarmItem.day6);

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox7);
        checkBoxDays.setChecked(alarmItem.day7);

        updateViewTime();

    }

    private void updateViewTime(){

        String strTime = AlarmItem.getTimeString(getBaseContext(), alarmItem);

        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText(strTime);

    }

    private void writeDataToItem(){

        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        alarmItem.name = editTextName.getText().toString();

        CheckBox checkBoxDays;

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox1);
        alarmItem.day1 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox2);
        alarmItem.day2 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox3);
        alarmItem.day3 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox4);
        alarmItem.day4 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox5);
        alarmItem.day5 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox6);
        alarmItem.day6 = checkBoxDays.isChecked();

        checkBoxDays = (CheckBox) findViewById(R.id.checkBox7);
        alarmItem.day7 = checkBoxDays.isChecked();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        alarmItem.time_hour = hourOfDay;
        alarmItem.time_minute = minute;

        updateViewTime();
    }

    //start change time to new
    public void onTimeClick(View v){

        Bundle bundle = new Bundle();
        bundle.putInt("hour", alarmItem.time_hour);
        bundle.putInt("minute", alarmItem.time_minute);

        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(bundle);
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");
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
