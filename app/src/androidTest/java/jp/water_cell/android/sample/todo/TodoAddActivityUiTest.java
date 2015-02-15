package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TodoAddActivityUiTest extends ActivityInstrumentationTestCase2<TodoAddActivity> {

    public TodoAddActivityUiTest() {
        super(TodoAddActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void 各ViewがInjectされていること() throws Exception {
        getActivity();

        onView(withId(R.id.toolbar)).check(matches(notNullValue()));
        onView(withId(R.id.et_title)).check(matches(notNullValue()));
        onView(withId(R.id.et_description)).check(matches(notNullValue()));
        onView(withId(R.id.btn_save)).check(matches(notNullValue()));
        onView(withId(R.id.sw_done)).check(matches(notNullValue()));
    }

    @Test
    public void Extraを付けずに起動した場合の初期化内容が正しいこと() throws Exception {
        getActivity();

        onView(withId(R.id.et_title)).check(matches(withText("")));
        onView(withId(R.id.et_description)).check(matches(withText("")));
        onView(withId(R.id.sw_done)).check(matches(isNotChecked()));
    }

    @Test
    public void Extraを付けて起動した場合の初期化内容が正しいこと() throws Exception {
        Todo todo = new Todo.Builder()
                .title("たいとるhogehoge")
                .description("ないよーhogehoge")
                .done(true)
                .build();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.putExtra(TodoAddActivity.EXTRA_EDIT_SOURCE, todo);
        setActivityIntent(intent);
        getActivity();

        onView(withId(R.id.et_title)).check(matches(withText("たいとるhogehoge")));
        onView(withId(R.id.et_description)).check(matches(withText("ないよーhogehoge")));
        onView(withId(R.id.sw_done)).check(matches(isChecked()));
    }
}
