package jp.water_cell.android.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class MainActivityTest {
    @Test
    public void 各Viewがインジェクトされている() {
        final MainActivity activity = Robolectric.buildActivity(MainActivity.class).create().get();

        assertThat(activity.toolbar, is(not(nullValue())));
        assertThat(activity.tvHello, is(not(nullValue())));
    }
}
