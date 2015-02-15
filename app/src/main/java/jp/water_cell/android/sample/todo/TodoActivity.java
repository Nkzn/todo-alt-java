package jp.water_cell.android.sample.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TodoActivity extends ActionBarActivity {

    private static final String TAG = TodoActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ADD = 0;
    private static final int REQUEST_CODE_EDIT = 1;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.list_view)
    ListView listView;

    TodoActivityModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        model = new TodoActivityModel(this);

        listView.setAdapter(model.getAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoActivity.this.onItemClick((Todo) parent.getItemAtPosition(position));
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TodoActivity.this.onItemLongClick((Todo) parent.getItemAtPosition(position));
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_add:
                startActivityForResult(new Intent(TodoActivity.this, TodoAddActivity.class), REQUEST_CODE_ADD);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_ADD:
                    final Todo newTodo = data.getParcelableExtra(TodoAddActivity.RESULT_CODE_TODO);
                    model.create(newTodo);
                case REQUEST_CODE_EDIT:
                    final Todo editedTodo = data.getParcelableExtra(TodoAddActivity.RESULT_CODE_TODO);
                    model.update(editedTodo);
                    break;
            }
        }
    }

    void onItemClick(final Todo todo) {

        new AlertDialog.Builder(this)
                .setItems(new String[]{
                        getString(R.string.edit),
                        getString(R.string.Delete)
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(TodoActivity.this, TodoAddActivity.class);
                                intent.putExtra(TodoAddActivity.EXTRA_EDIT_SOURCE, todo);
                                startActivityForResult(intent, REQUEST_CODE_EDIT);
                                break;
                            case 1:
                                deleteTodo(todo);
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .show();
    }

    void onItemLongClick(Todo todo) {
        Todo doneToggled = new Todo.Builder(todo)
                .done(!todo.isDone())
                .build();

        model.update(doneToggled);
    }

    void deleteTodo(Todo todo) {
        model.delete(todo.getId());
    }
}
