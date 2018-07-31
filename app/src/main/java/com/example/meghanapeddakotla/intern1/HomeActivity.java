package com.example.meghanapeddakotla.intern1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private TextView textView;
    private TextView Day;
    private ImageButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textView = (TextView)findViewById(R.id.text_view1);
        Day = (TextView)findViewById(R.id.text_view2);
        add = (ImageButton)findViewById(R.id.imageButton);

        String currentDateString = DateFormat.getDateInstance().format(new Date());
        textView.setText(currentDateString);

        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        Day.setText(weekDay);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AddsessionActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
       menuInflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
                AlertDialog.Builder dialogBulider = new AlertDialog.Builder(HomeActivity.this);
                dialogBulider.setTitle("logout");
                dialogBulider.setMessage("Click 'Ok' to Logout from the session");
                dialogBulider.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                    }
                });
                AlertDialog alertDialog = dialogBulider.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

