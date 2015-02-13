package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends ActionBarActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_ADD = 0;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.list_view)
    ListView listView;

    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo.Builder()
                .id(1)
                .title("タイトル１")
                .description("なかみ１")
                .timestamp(1234567890)
                .done(true)
                .build());
        todos.add(new Todo.Builder()
                .id(2)
                .title("タイトル２")
                .description("なかみ２")
                .timestamp(1234567890)
                .done(false)
                .build());
        todos.add(new Todo.Builder()
                .id(3)
                .title("タイトル３")
                .description("なかみ３")
                .timestamp(1234567890)
                .done(true)
                .build());

        adapter = new TodoAdapter(this, todos);

        listView.setAdapter(adapter);
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
                startActivityForResult(new Intent(MainActivity.this, TodoAddActivity.class), REQUEST_CODE_ADD);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_ADD) {
            final Todo newTodo = data.getParcelableExtra(TodoAddActivity.RESULT_CODE_TODO);
            adapter.add(newTodo);
        }
    }
}
