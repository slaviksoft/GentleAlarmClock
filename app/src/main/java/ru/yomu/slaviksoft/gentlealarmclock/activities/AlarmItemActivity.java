package ru.yomu.slaviksoft.gentlealarmclock.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;
import ru.yomu.slaviksoft.gentlealarmclock.R;

public class AlarmItemActivity extends AppCompatActivity {

    private AlarmItem alarmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_item);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmItem = getIntent().getParcelableExtra("item");
        readFromItem();

        Button buttonOK = (Button) findViewById(R.id.buttonOK);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToItem();
                Intent i = getIntent().putExtra("item", alarmItem);
                setResult(RESULT_OK, i);
                finish();
            }
        });

    }

    private void readFromItem(){

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

        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        textViewTime.setText(alarmItem.time);

    }

    private void writeToItem(){

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

        TextView textViewTime = (TextView) findViewById(R.id.textViewTime);
        alarmItem.time = textViewTime.getText().toString();

    }


}
