package com.android.musty;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyCalendarActivity extends AppCompatActivity {
    private Button add_date;
    CalendarView cal;
    TextView look;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        Intent intent = getIntent();
        final String Name = intent.getStringExtra("name");

        final DateFormat df = new SimpleDateFormat("d/M/yyyy");
        add_date = findViewById(R.id.add_date);
        cal = findViewById(R.id.calendarView);
        look = findViewById(R.id.datalook);
        final String date = df.format(cal.getDate());
        look.setText(date);

        add_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date2 = look.getText().toString();
                Intent intent = new Intent(MyCalendarActivity.this, CreateEditActivity.class);
                intent.putExtra("name", Name);
                intent.putExtra("date", date2);
                startActivity(intent);
            }

        });
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView cal, int year, int month, int day) {
                String curDate = Integer.toString(day)+"/" + Integer.toString(month+1)+"/"+Integer.toString(year);
                TextView f = findViewById(R.id.datalook);
                f.setText(curDate);
            }
        });
    }
}

