package jp.water_cell.android.sample.todo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class TodoActivityModelTest {

    TodoActivityModel model;

    @Before
    public void setUp() throws Exception {
        model = new TodoActivityModel(Robolectric.application);
    }

    @Test
    public void 作ったデータが出てくる() throws Exception {
        Todo todo = new Todo.Builder()
                .id(1)
                .title("タイトル")
                .description("なかみ")
                .timestamp(System.currentTimeMillis())
                .done(true)
                .build();

        model.create(todo);

        final List<Todo> actual = model.getAll();

        assertThat(actual, hasItem(todo));
    }

    @Test
    public void データが編集できる() throws Exception {
        Todo todo1 = new Todo.Builder()
                .id(1)
                .title("タイトル1")
                .description("なかみ1")
                .timestamp(System.currentTimeMillis())
                .done(true)
                .build();

        model.create(todo1);

        Todo todo2 = new Todo.Builder()
                .id(1)
                .title("タイトル2")
                .description("なかみ2")
                .timestamp(System.currentTimeMillis())
                .done(false)
                .build();

        model.update(todo2);

        final List<Todo> actual = model.getAll();

        assertThat(actual, not(hasItem(todo1)));
        assertThat(actual, hasItem(todo2));
    }

    @Test
    public void データが削除できる() throws Exception {
        Todo todo1 = new Todo.Builder()
                .id(1)
                .title("タイトル1")
                .description("なかみ1")
                .timestamp(System.currentTimeMillis())
                .done(true)
                .build();
        Todo todo2 = new Todo.Builder()
                .id(2)
                .title("タイトル2")
                .description("なかみ2")
                .timestamp(System.currentTimeMillis())
                .done(false)
                .build();
        Todo todo3 = new Todo.Builder()
                .id(3)
                .title("タイトル3")
                .description("なかみ3")
                .timestamp(System.currentTimeMillis())
                .done(false)
                .build();

        model.create(todo1);
        model.create(todo2);
        model.create(todo3);

        model.delete(todo2.getId());

        final List<Todo> actual = model.getAll();

        assertThat(actual, hasItem(todo1));
        assertThat(actual, not(hasItem(todo2)));
        assertThat(actual, hasItem(todo3));
    }

}