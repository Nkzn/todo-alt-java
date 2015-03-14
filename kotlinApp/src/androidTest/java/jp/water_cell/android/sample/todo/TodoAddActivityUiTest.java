package jp.water_cell.android.sample.todo;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.notNullValue;

@LargeTest
public class TodoAddActivityUiTest extends ActivityInstrumentationTestCase2<TodoAddActivity> {

    public TodoAddActivityUiTest() {
        super(TodoAddActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    public void test_各ViewがInjectされていること() throws Exception {
        getActivity();

        onView(withId(R.id.toolbar)).check(matches(notNullValue()));
        onView(withId(R.id.et_title)).check(matches(notNullValue()));
        onView(withId(R.id.et_description)).check(matches(notNullValue()));
        onView(withId(R.id.btn_save)).check(matches(notNullValue()));
        onView(withId(R.id.sw_done)).check(matches(notNullValue()));
    }

    public void test_Extraを付けずに起動した場合の初期化内容が正しいこと() throws Exception {
        getActivity();

        onView(withId(R.id.et_title)).check(matches(withText("")));
        onView(withId(R.id.et_description)).check(matches(withText("")));
        onView(withId(R.id.sw_done)).check(matches(isNotChecked()));
    }

    public void test_Extraを付けて起動した場合の初期化内容が正しいこと() throws Exception {
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
