package com.example.meghanapeddakotla.intern1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class EventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =getMenuInflater();
        menuInflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
                AlertDialog.Builder dialogBulider = new AlertDialog.Builder(EventsActivity.this);
                dialogBulider.setTitle("logout");
                dialogBulider.setMessage("Click 'Ok' to Logout from the session");
                dialogBulider.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        startActivity(new Intent(EventsActivity.this,LoginActivity.class));
                    }
                });
                AlertDialog alertDialog = dialogBulider.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
