package ru.yomu.slaviksoft.gentlealarmclock;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class AlarmsListActivity extends AppCompatActivity {

    private ListView listView;
    private DbHelper dbHelper;
    AlarmsManager alarmsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms_list);

        alarmsManager = new AlarmsManager(this);

        listView = (ListView) findViewById(R.id.listViewAlarms);
        listView.setAdapter(alarmsManager.getCursorAdapter());
        listView.setOnItemLongClickListener(new AlertItemLongClickListener());
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

    public void onAddClick(View view) {

        alarmsManager.addAlarm();

    }

    private class AlertItemLongClickListener implements AdapterView.OnItemLongClickListener{

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

            return false;
        }

    }

}
