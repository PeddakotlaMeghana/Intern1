package com.example.meghanapeddakotla.intern1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddsessionActivity extends AppCompatActivity {
    private Spinner department;
    private Spinner sem;
    private Button save;
    private Button pickDate;
    private Button picktime;
    private EditText textView;
    private EditText time;
    private TextView text;
    private int mYear,mMonth,mDay,hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsession);

         department = (Spinner) findViewById(R.id.department_spinner);
         sem = (Spinner) findViewById(R.id.sem_spinner);
         save = (Button) findViewById(R.id.save_button);
         pickDate = (Button) findViewById(R.id.pick_date);
         textView = (EditText) findViewById(R.id.date);
         time = (EditText) findViewById(R.id.et_time);
         picktime = (Button) findViewById(R.id.pick_time);
         text = (TextView) findViewById(R.id.textView3);

        String[] items = new String[] { "DEPARTMENT" , "CSE", "ISE", "EEE" , "ECE" , "CIVIL" , "MECHANICAL" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        department.setAdapter(adapter);

        String[] items1 = new String[] { "SEMESTER" , "1", "2", "3" , "4" , "5" , "6" , "7" , "8"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        sem.setAdapter(adapter1);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddsessionActivity.this, EventsActivity.class));
            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(sdf.format(myCalendar.getTime()));
            }


        };
        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dpd = new DatePickerDialog(AddsessionActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        if (year < mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear,mMonth,mDay);

                        textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(System.currentTimeMillis());
                dpd.show();

            }
        });
       picktime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Calendar mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);
               TimePickerDialog mTimePicker;
               mTimePicker = new TimePickerDialog(AddsessionActivity.this, new TimePickerDialog.OnTimeSetListener()
               {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                   {
                       time.setText( "" + selectedHour + ":" + selectedMinute);
                   }
               }, hour, minute, false);
               mTimePicker.setTitle("Select Time");
               mTimePicker.show();
           }
       });

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
                AlertDialog.Builder dialogBulider = new AlertDialog.Builder(AddsessionActivity.this);
                dialogBulider.setTitle("logout");
                dialogBulider.setMessage("Click 'Ok' to Logout from the session");
                dialogBulider.setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        startActivity(new Intent(AddsessionActivity.this,LoginActivity.class));
                    }
                });
                AlertDialog alertDialog = dialogBulider.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
