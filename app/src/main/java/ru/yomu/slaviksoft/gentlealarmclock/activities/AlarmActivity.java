package ru.yomu.slaviksoft.gentlealarmclock.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import ru.yomu.slaviksoft.gentlealarmclock.AlarmItem;
import ru.yomu.slaviksoft.gentlealarmclock.R;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        AlarmItem alarm = getIntent().getParcelableExtra("ALARM");

        TextView info = (TextView) findViewById(R.id.textViewInfo);
        info.setText( alarm.name);

    }
}
