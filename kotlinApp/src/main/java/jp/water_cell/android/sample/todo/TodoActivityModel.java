package jp.water_cell.android.sample.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoActivityModel {

    final TodoAdapter adapter;
    final DatabaseHelper helper;

    public TodoActivityModel(Context context) {
        adapter = new TodoAdapter(context, new ArrayList<Todo>());
        helper = new DatabaseHelper(context);
    }

    public TodoAdapter getAdapter() {
        refreshAdapter();
        return adapter;
    }

    public void create(Todo todo) {

        final SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Todo.COLUMN_NAME_TITLE, todo.getTitle());
        contentValues.put(Todo.COLUMN_NAME_DESCRIPTION, todo.getDescription());
        contentValues.put(Todo.COLUMN_NAME_TIMESTAMP, todo.getTimestamp());
        contentValues.put(Todo.COLUMN_NAME_DONE, todo.isDone() ? 1 : 0);

        db.insert(Todo.TABLE_NAME, null, contentValues);

        refreshAdapter();
    }

    public void update(Todo todo) {

        final SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Todo.COLUMN_NAME_TITLE, todo.getTitle());
        contentValues.put(Todo.COLUMN_NAME_DESCRIPTION, todo.getDescription());
        contentValues.put(Todo.COLUMN_NAME_TIMESTAMP, todo.getTimestamp());
        contentValues.put(Todo.COLUMN_NAME_DONE, todo.isDone() ? 1 : 0);

        db.update(Todo.TABLE_NAME, contentValues, Todo.COLUMN_NAME_ID + "=" + todo.getId(), null);

        refreshAdapter();
    }

    public void delete(long id) {

        final SQLiteDatabase db = helper.getWritableDatabase();

        db.delete(Todo.TABLE_NAME, Todo.COLUMN_NAME_ID + "=" + id, null);

        refreshAdapter();
    }

    private void refreshAdapter() {
        adapter.clear();
        adapter.addAll(getAll());
    }

    List<Todo> getAll() {

        final SQLiteDatabase db = helper.getReadableDatabase();
        final Cursor cursor = db.query(Todo.TABLE_NAME, null, null, null, null, null, null);

        List<Todo> todos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Todo todo = new Todo.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(Todo.COLUMN_NAME_ID)))
                    .title(cursor.getString(cursor.getColumnIndex(Todo.COLUMN_NAME_TITLE)))
                    .description(cursor.getString(cursor.getColumnIndex(Todo.COLUMN_NAME_DESCRIPTION)))
                    .timestamp(cursor.getLong(cursor.getColumnIndex(Todo.COLUMN_NAME_TIMESTAMP)))
                    .done(cursor.getInt(cursor.getColumnIndex(Todo.COLUMN_NAME_DONE)) != 0)
                    .build();
            todos.add(todo);
        }

        cursor.close();
        db.close();

        return todos;
    }

}
