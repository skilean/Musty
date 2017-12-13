package com.android.musty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


public class TableInteraction {
    private SQLiteDatabase db;

    /**
     * Конструктор. Для чтения из базы данных необходимо создать объект TableInteraction.
     * и использовать методы внутри объекта.
     * @param context контекст активити (getApplicationContext).
     */
    public TableInteraction (Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getReadableDatabase();
    }

    /**
     * Читает из таблицы в базе данных заметки указанной категории.
     * ПРОВЕРЯЙТЕ - ПУСТА ЛИ ВОЗВРАЩАЕМАЯ ТАБЛИЦА!!!
     * @param table_name имя таблицы.
     * @param note_category категория заметок.
     * @return двумерный массив, содержащий таблицу.
     */
    public Object[][] readtable(String table_name, String note_category){
        // Курсор для получения данных из таблицы.
        Cursor cursor = db.rawQuery("select * from " + table_name +
                "where note_categoty = " + note_category + ";", null);
        // Массив для хранения таблицы.
        Object[][] note_table = new Object[][] {null};
        int cPosition; // Позиция курсора

        // Если курсор не пуст, то переписываем данные в массив.
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            note_table = new Object[cursor.getCount()][6];

            do {
                cPosition = cursor.getPosition();

                note_table[cPosition][0] = cursor.getInt(cursor.getColumnIndex(DBHelper.Columns.NOTE_ID));
                note_table[cPosition][1] = cursor.getString(cursor.getColumnIndex(DBHelper.Columns.NOTE_HEADER));
                note_table[cPosition][2] = cursor.getString(cursor.getColumnIndex(DBHelper.Columns.NOTE_TAGS));
                note_table[cPosition][3] = cursor.getString(cursor.getColumnIndex(DBHelper.Columns.NOTE_DATE));
                note_table[cPosition][4] = cursor.getString(cursor.getColumnIndex(DBHelper.Columns.NOTE_CATEGORY));
                note_table[cPosition][5] = cursor.getString(cursor.getColumnIndex(DBHelper.Columns.NOTE_MEDIA));

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        } else {
            Log.e("db read", "Ошибка readTable, таблица пуста, либо ее не удалось прочитать.");
        }

        cursor.close();

        return note_table;
    }

    /**
     * Добавляет заметку в базу данных.
     * @param table_name имя таблицы.
     * @param note_header заголовок заметки.
     * @param note_tags теги заметки, разделенные пробелами.
     * @param note_date дата исполнения.
     * @param note_category категория.
     * @param note_media ссылка на информацию из медиа-ресурса.
     */
    public void addNote(String table_name, String note_header, String note_tags,
                        String note_date, String note_category, String note_media) {

        ContentValues values = new ContentValues();

        // Значения заметки
        values.put(DBHelper.Columns.NOTE_HEADER, note_header);
        values.put(DBHelper.Columns.NOTE_TAGS, note_tags);
        values.put(DBHelper.Columns.NOTE_DATE, note_date);
        values.put(DBHelper.Columns.NOTE_CATEGORY, note_category);
        values.put(DBHelper.Columns.NOTE_MEDIA, note_media);

        db.insert(table_name, null, values);

        Log.d("db insert","Добавлена заметка.");
    }

    /**
     * Добавляет заметку в базу данных.
     * @param table_name имя таблицы.
     * @param note_header заголовок заметки.
     * @param note_tags теги заметки, разделенные пробелами.
     * @param note_date дата исполнения.
     * @param note_category категория.
     * @param note_media ссылка на информацию из медиа-ресурса.
     */
    public void editNote(String table_name, int note_id, String note_header, String note_tags,
                        String note_date, String note_category, String note_media) {
        String selection = DBHelper.Columns.NOTE_ID + " = " + note_id;

        ContentValues values = new ContentValues();

        // Новые значения заметки
        values.put(DBHelper.Columns.NOTE_HEADER, note_header);
        values.put(DBHelper.Columns.NOTE_TAGS, note_tags);
        values.put(DBHelper.Columns.NOTE_DATE, note_date);
        values.put(DBHelper.Columns.NOTE_CATEGORY, note_category);
        values.put(DBHelper.Columns.NOTE_MEDIA, note_media);

        db.update(table_name, values, selection, null);

        Log.d("db insert","Изменена заметка.");
    }

    /**
     * Удаляет заметку из таблицы.
     * @param table_name имя таблицы.
     * @param note_id id удаляемой заметки.
     */
    public void deleteNote(String table_name, int note_id) {
        String selection = DBHelper.Columns.NOTE_ID + " = " + note_id;

        db.delete(table_name, selection, null);

        Log.d("db insert","Удалена заметка.");
    }
}
