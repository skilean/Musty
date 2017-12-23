package com.android.musty;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;



public class Calendar2 extends AppCompatActivity {
    CalendarView cal2;
    TextView look;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);
        cal2 = findViewById(R.id.cal2);
        look = findViewById(R.id.check);
        cal2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
            public void onSelectedDayChange(@NonNull CalendarView cal2, int year, int month, int day) {
               TableInteraction TI = new TableInteraction(Calendar2.this.getApplicationContext());
               Object[][] not = TI.readtable(DBHelper.Columns.TABLE_NAME, "Фильмы");
               String curDate = Integer.toString(day)+"/" + Integer.toString(month+1)+"/"+Integer.toString(year);
               String res = "Ваши заметки на данную дату";
               int k = 1;
               if(not != null) {
                   for (int i = 0; i < not.length; i++) {
                       if (not[i][3].equals(curDate)) {
                           res = res + "\n" + k + "." + not[i][1] + " - Фильм";
                           k++;
                       }

                   }
               }
                   Object[][] not1 = TI.readtable(DBHelper.Columns.TABLE_NAME, "Сериалы");
                   if(not1 != null) {
                       for (int i = 0; i < not1.length; i++) {
                           if (not1[i][3].equals(curDate)) {
                               res = res + "\n" + k + "." + not1[i][1] + " - Сериал";
                               k++;
                           }

                       }
               }
               Object[][] not2 = TI.readtable(DBHelper.Columns.TABLE_NAME, "Книги");
               if(not2 != null) {
                   for (int i = 0; i < not2.length; i++) {
                       if (not2[i][3].equals(curDate)) {
                           res = res + "\n" + k + "." + not2[i][1] + " - Книга";
                           k++;
                       }

                   }
               }
               Object[][] not3 = TI.readtable(DBHelper.Columns.TABLE_NAME, "Игры");
               if(not3 != null) {
                   for (int i = 0; i < not3.length; i++) {
                       if (not3[i][3].equals(curDate)) {
                           res = res + "\n" + k + "." + not3[i][1] + " - Игра";
                           k++;
                       }

                   }
               }
               look.setText(res);
            }
        });
    }
}