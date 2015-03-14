package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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

        Todo editSource = getIntent().getParcelableExtra(EXTRA_EDIT_SOURCE);
        if (editSource != null) {
            setTitle(R.string.edit_todo);
        }

        if (savedInstanceState == null) {
            if (editSource != null) {
                etTitle.setText(editSource.getTitle());
                etDescription.setText(editSource.getDescription());
                swDone.setChecked(editSource.isDone());
            }
        }

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

    @OnClick(R.id.btn_save)
    void onClickSave() {
        Todo editSource = getIntent().getParcelableExtra(EXTRA_EDIT_SOURCE);

        Todo todo = (editSource == null ? new Todo.Builder() : new Todo.Builder(editSource))
                .title(etTitle.getText().toString())
                .description(etDescription.getText().toString())
                .timestamp(System.currentTimeMillis())
                .done(swDone.isChecked())
                .build();

        Intent intent = new Intent();
        intent.putExtra(RESULT_CODE_TODO, todo);

        setResult(RESULT_OK, intent);
        finish();
    }
}
