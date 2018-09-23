package ru.rubiconepro.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends BaseAdapter {

    private List<MessageModel> messages;
    private Context context;
    private LayoutInflater inflater;

    public MainAdapter(Context context) {
        this.context = context;
        messages = new ArrayList<>();

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addMessage(MessageModel model) {
        messages.add(model);
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.message_item, parent, false);
        }

        TextView user = convertView.findViewById(R.id.tv_user);
        TextView message = convertView.findViewById(R.id.tv_message);

        MessageModel model = messages.get(position);

        user.setText(model.user);
        message.setText(model.message);

        return convertView;
    }
}
