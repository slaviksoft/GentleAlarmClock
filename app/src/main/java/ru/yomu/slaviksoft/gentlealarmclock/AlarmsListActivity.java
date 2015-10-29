package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ru.yomu.slaviksoft.gentlealarmclock.activities.AlarmItemActivity;


public class AlarmsListActivity extends AppCompatActivity {

    static public final String TAG = "test alarm list";

    public static final int REQUEST_ADD = 1000;
    public static final int REQUEST_EDIT = 1001;

    private ListView listView;
    private AlarmsManager alarmsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new AddClickListener());

        alarmsManager = new AlarmsManager(this);

        listView = (ListView) findViewById(R.id.listViewAlarms);
        listView.setAdapter(alarmsManager.getCursorAdapter());
        listView.setOnItemLongClickListener(new AlertItemLongClickListener());
        listView.setOnItemClickListener(new AlertItemClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarms_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // DELETE ITEM
    private class AlertItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            AlertDialog.Builder builder = new AlertDialog.Builder(AlarmsListActivity.this);
            builder.setTitle("Delete alarm")
                    .setMessage("Delete?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            alarmsManager.deleteAlarm(position);
                        }
                    })
                    .setNegativeButton(android.R.string.no, null).show();

            return true;
        }

    }

    // EDIT ITEM
    private class AlertItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            AlarmItem alarmItem = alarmsManager.getAlarmItem(position);
            Intent i = new Intent(AlarmsListActivity.this, AlarmItemActivity.class);
            i.putExtra("item", alarmItem);
            i.putExtra("position", position);
            startActivityForResult(i, REQUEST_EDIT);

        }
    }


    // ADD ITEM
    private class AddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            AlarmItem alarmItem = alarmsManager.getNewAlarmItem();

            Intent i = new Intent(AlarmsListActivity.this, AlarmItemActivity.class);
            i.putExtra("item", alarmItem);
            startActivityForResult(i, REQUEST_ADD);

        }
    }


    // RESULT OF ITEM CHANGE
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == REQUEST_ADD) {
                AlarmItem alarmItem = data.getParcelableExtra("item");
                alarmsManager.addAlarm(alarmItem);
            }

            if (requestCode == REQUEST_EDIT) {
                AlarmItem alarmItem = data.getParcelableExtra("item");
                int position = data.getIntExtra("position", -1);
                alarmsManager.updateAlarm(position, alarmItem);
            }

        }
    }
}
