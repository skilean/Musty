package com.android.musty;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button CreateEditButton;
    private Button InfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CreateEditButton = findViewById(R.id.add_button);
        CreateEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CreateEditActivity.class);
                startActivity(intent);
                }
        });
              
        InfoButton = findViewById(R.id.info_button);    
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, InfoActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Если не хотим сохранять историю
                startActivity(intent);
            }
        });
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, "DB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            Log.d("log","--- onCreate databse ---");

            db.execSQL("create table notes ("
                    + "note_id integer primary key autoincrement,"
                    + "note_header text,"
                    + "note_tags text,"
                    + "note_date text,"
                    + "note_category text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
    }
}
