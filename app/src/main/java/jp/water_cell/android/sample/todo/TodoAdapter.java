package jp.water_cell.android.sample.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TodoAdapter extends ArrayAdapter<Todo> {

    final LayoutInflater inflater;

    public TodoAdapter(Context context, List<Todo> objects) {
        super(context, 0, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        final Todo item = getItem(position);
        holder.tvId.setText(String.format("#%1$d", item.getId()));
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvTimestamp.setText(new DateTime(item.getTimestamp()).toString("yyyy.MM.dd HH:mm:ss"));
        holder.tvChecked.setVisibility(item.isDone() ? View.VISIBLE : View.GONE);

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_id)
        TextView tvId;

        @InjectView(R.id.tv_title)
        TextView tvTitle;

        @InjectView(R.id.tv_description)
        TextView tvDescription;

        @InjectView(R.id.tv_timestamp)
        TextView tvTimestamp;

        @InjectView(R.id.tv_checked)
        TextView tvChecked;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}