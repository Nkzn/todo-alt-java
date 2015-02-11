package jp.water_cell.android.sample.todo;

import android.os.Bundle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class TodoTest {

    @Test
    public void Bundleに入れて出しても同じものが入っていること() {

        Todo actual = new Todo();
        actual.setId(111);
        actual.setTimestamp(12345);
        actual.setTitle("たいとる");
        actual.setDescription("なかみ");
        actual.setDone(false);

        Bundle bundle = new Bundle();
        bundle.putParcelable("hoge", actual);

        Todo expected = bundle.getParcelable("hoge");

        assertThat(actual, is(expected));
    }

}