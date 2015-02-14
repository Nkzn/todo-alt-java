package jp.water_cell.android.sample.todo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class TodoActivityModel {

    TodoAdapter adapter;

    public TodoActivityModel(Context context) {
        adapter = new TodoAdapter(context, new ArrayList<Todo>());
    }

    public TodoAdapter getAdapter() {
        return adapter;
    }

    public void create(Todo todo) {

        adapter.clear();
        adapter.addAll(getAll());
    }

    private List<Todo> getAll() {
        return null;
    }

    public void update(Todo todo) {

        adapter.clear();
        adapter.addAll(getAll());
    }

    public void delete(long id) {

        adapter.clear();
        adapter.addAll(getAll());
    }
}
