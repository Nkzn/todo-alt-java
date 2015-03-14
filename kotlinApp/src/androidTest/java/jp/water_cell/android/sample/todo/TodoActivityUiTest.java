package jp.water_cell.android.sample.todo;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@LargeTest
public class TodoActivityUiTest extends ActivityInstrumentationTestCase2<TodoActivity> {

    TodoActivity activity;

    public TodoActivityUiTest() {
        super(TodoActivity.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activity = getActivity();
    }

    public void test_各ViewがInjectされていること() {
        getActivity();

        onView(withId(R.id.toolbar)).check(matches(notNullValue()));
        onView(withId(R.id.list_view)).check(matches(notNullValue()));

    }
}
