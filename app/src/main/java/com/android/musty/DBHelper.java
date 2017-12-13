package com.android.musty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    private static final int db_version = 1;

    // Класс содержит данные о таблице заметок и некоторый свойства класса
    public static abstract class Columns implements BaseColumns {
        public static final String TABLE_NAME = "notes", // Название таблицы для хранения заметок
                NOTE_ID = "note_id", // Идентификатор заметки
                NOTE_HEADER = "note_header", // Заголовок/название заметки
                NOTE_TAGS = "note_tags", // Строка с тегами, разделенными пробелами
                NOTE_DATE = "note_date", // Назначенная дата
                NOTE_CATEGORY = "note_category", // Категория заметки
                NOTE_MEDIA = "note_media"; // Ссылка на информацию из медиасервиса
    }

    // SQL-запрос для создания базы данных
    private final String create_table = "create table if not exists " + Columns.TABLE_NAME + "("
            + Columns.NOTE_ID + " integer primary key autoincrement, "
            + Columns.NOTE_HEADER + " text, "
            + Columns.NOTE_TAGS + " text, "
            + Columns.NOTE_DATE + " text, "
            + Columns.NOTE_CATEGORY + " text "
            + Columns.NOTE_MEDIA + " text " + ");";

    /**
     * Конструктор.
     * @param context контекст вызова
     */
    public DBHelper(Context context) {
        super(context, "DB", null, db_version);
    }

    /**
     * Создает базу данных
     * @param db объект SQLiteDatabase для запроса к базе данных
     */
    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("db","Создана база банных.");

        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
