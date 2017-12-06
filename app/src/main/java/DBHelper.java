import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
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
