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
        refreshAdapter();
        return adapter;
    }

    public void create(Todo todo) {

        refreshAdapter();
    }

    public void update(Todo todo) {

        refreshAdapter();
    }

    public void delete(long id) {

        refreshAdapter();
    }

    private void refreshAdapter() {
        adapter.clear();
        adapter.addAll(getAll());
    }

    List<Todo> getAll() {
        return null;
    }

}
