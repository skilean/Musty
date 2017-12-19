package com.android.musty;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button CreateEditButton;
    private Button InfoButton;
    private Button FilmsButton, SeriesButton, BooksButton, GamesButton;

    ArrayList<Note> notes = new ArrayList<Note>();
    BoxAdapter boxAdapter;
    DBHelper dbHelper;

    ListView lvMain; //= (ListView) findViewById(R.id.lvMain);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        final SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.onCreate(database);

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

        FilmsButton = findViewById(R.id.film_button);
        FilmsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                fillData("Фильмы");
                boxAdapter = new BoxAdapter(MainActivity.this, notes);

                lvMain = findViewById(R.id.lvMain);
                lvMain.setAdapter(boxAdapter);
            }
        });

        SeriesButton = findViewById(R.id.series_button);
        SeriesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                // создаем адаптер
                fillData("Сериалы");
                boxAdapter = new BoxAdapter(MainActivity.this, notes);

                // настраиваем список
                lvMain = findViewById(R.id.lvMain);
                lvMain.setAdapter(boxAdapter);
            }
        });

        BooksButton = findViewById(R.id.books_button);
        BooksButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                // создаем адаптер
                fillData("Книги");
                boxAdapter = new BoxAdapter(MainActivity.this, notes);

                // настраиваем список
                lvMain = findViewById(R.id.lvMain);
                lvMain.setAdapter(boxAdapter);
            }
        });

        GamesButton = findViewById(R.id.games_button);
        GamesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                // создаем адаптер
                fillData("Игры");
                boxAdapter = new BoxAdapter(MainActivity.this, notes);

                // настраиваем список
                lvMain = findViewById(R.id.lvMain);
                lvMain.setAdapter(boxAdapter);

            }
        });
    }

    //поидее тут надо заполнять заметки данными из БД
    void fillData(String category) {
        TableInteraction TI = new TableInteraction(MainActivity.this.getApplicationContext());
        Object[][] not = TI.readtable(DBHelper.Columns.TABLE_NAME, category);
        if(not != null) {
            for (int i = 0; i < not.length; i++) {
                Log.d("table read", not[i][1].toString() + not[i][2].toString() + not[i][3].toString() + not[i][4].toString());
                notes.add(new Note(not[i][1].toString(), not[i][2].toString(), not[i][3].toString(), not[i][4].toString(), not[i][0].toString()));
            }
        }
    }
}
