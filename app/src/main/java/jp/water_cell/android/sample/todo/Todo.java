package jp.water_cell.android.sample.todo;

import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class Todo implements Parcelable {

    public static final String TABLE_NAME = "todos";
    public static final String COLUMN_NAME_ID = BaseColumns._ID;
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    public static final String COLUMN_NAME_DONE = "done";

    long id;
    long timestamp;
    String title;
    String description;
    boolean done;

    private Todo(Builder builder) {
        this.id = builder.id;
        this.timestamp = builder.timestamp;
        this.title = builder.title;
        this.description = builder.description;
        this.done = builder.done;
    }

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (done != todo.done) return false;
        if (id != todo.id) return false;
        if (timestamp != todo.timestamp) return false;
        if (description != null ? !description.equals(todo.description) : todo.description != null)
            return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (done ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.timestamp);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeByte(done ? (byte) 1 : (byte) 0);
    }

    public Todo() {
    }

    private Todo(Parcel in) {
        this.id = in.readLong();
        this.timestamp = in.readLong();
        this.title = in.readString();
        this.description = in.readString();
        this.done = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>() {
        public Todo createFromParcel(Parcel source) {
            return new Todo(source);
        }

        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public static class Builder {
        private long id;
        private long timestamp;
        private String title;
        private String description;
        private boolean done;

        public Builder() {
        }

        public Builder(Todo source) {
            if (source == null) {
                throw new IllegalArgumentException("source must not be null");
            }

            this.id = source.getId();
            this.timestamp = source.getTimestamp();
            this.title = source.getTitle();
            this.description = source.getDescription();
            this.done = source.isDone();
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder done(boolean done) {
            this.done = done;
            return this;
        }

        public Todo build() {
            return new Todo(this);
        }
    }
}
