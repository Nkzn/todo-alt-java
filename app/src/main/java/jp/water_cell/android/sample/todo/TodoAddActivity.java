package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class TodoAddActivity extends ActionBarActivity {

    public static final String RESULT_CODE_TODO = TodoAddActivity.class.getCanonicalName() + ".result_todo";
    public static final String EXTRA_EDIT_SOURCE = TodoAddActivity.class.getCanonicalName() + ".edit_source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button button = new Button(this);
        button.setText("push");
        button.setOnClickListener(new View.OnClickListener() {
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

        setContentView(button);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
