package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TodoAddActivity extends ActionBarActivity {

    public static final String RESULT_CODE_TODO = TodoAddActivity.class.getCanonicalName() + ".result_todo";
    public static final String EXTRA_EDIT_SOURCE = TodoAddActivity.class.getCanonicalName() + ".edit_source";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.et_title)
    EditText etTitle;

    @InjectView(R.id.et_description)
    EditText etDescription;

    @InjectView(R.id.sw_done)
    Switch swDone;

    @InjectView(R.id.btn_save)
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_add);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Random rand = new Random();
                long id = rand.nextLong();

                Todo todo = new Todo.Builder()
                        .id(id)
                        .title("タイトル" + id)
                        .description("なかみ" + id)
                        .timestamp(1234567890)
                        .done(true)
                        .build();

                Intent intent = new Intent();
                intent.putExtra(RESULT_CODE_TODO, todo);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
