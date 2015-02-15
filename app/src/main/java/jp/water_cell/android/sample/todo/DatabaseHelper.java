package jp.water_cell.android.sample.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "todo.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql = "CREATE TABLE " + Todo.TABLE_NAME + " ("
                + Todo.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Todo.COLUMN_NAME_TITLE + " TEXT, "
                + Todo.COLUMN_NAME_DESCRIPTION + " TEXT, "
                + Todo.COLUMN_NAME_TIMESTAMP + " INTEGER, "
                + Todo.COLUMN_NAME_DONE + " INTEGER" // 0でfalse, それ以外はtrue
                + ");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
