package jp.water_cell.android.template.espresso;

import android.test.ActivityInstrumentationTestCase2;

import jp.water_cell.android.template.MainActivity;
import jp.water_cell.android.template.R;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.*;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @SuppressWarnings("deprecation")
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    public void testTvDisplay() {
        onView(withId(R.id.tv_hello)).check(matches(withText("Hello world!")));
    }
}
