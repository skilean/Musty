package com.android.musty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button CreateEditButton;
    private Button InfoButton;

    ArrayList<Note> notes = new ArrayList<Note>();
    BoxAdapter boxAdapter;

    TableInteraction TI = new TableInteraction(MainActivity.this.getApplicationContext());

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

        // создаем адаптер
      // fillData();
        boxAdapter = new BoxAdapter(this, notes);

        // настраиваем список
        //ListView lvMain = (ListView) findViewById(R.id.lvMain);
       //lvMain.setAdapter(boxAdapter);
    }

    //поидее тут надо заполнять заметки данными из БД
    /*
    void fillData() {

        Object[][] not = TI.readtable(DBHelper.Columns.TABLE_NAME, "Фильмы");

      for (int i = 1; i <= not.length; i++) {

            notes.add(new Note(not[i][1].toString(), not[i][2].toString(), not[i][3].toString(), not[i][4].toString()));
        }
    }*/
}
