package com.example.myHealth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SignalMessageAdapter extends ArrayAdapter<SignalMessage> {

    public static final int VIEW_TYPE_LOCAL = 0;
    public static final int VIEW_TYPE_REMOTE = 1;
    private static final Map<Integer, Integer> viewTypes;

    static {
        Map<Integer, Integer> aMap = new HashMap<>();
        //aMap.put(VIEW_TYPE_LOCAL, R.layout.message_single_local);
        //aMap.put(VIEW_TYPE_REMOTE, R.layout.message_single_remote);
        aMap.put(VIEW_TYPE_LOCAL, R.layout.message_me);
        aMap.put(VIEW_TYPE_REMOTE, R.layout.message_other);
        viewTypes = Collections.unmodifiableMap(aMap);
    }

    public SignalMessageAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SignalMessage message = getItem(position);

        if (convertView == null) {
            int type = getItemViewType(position);
            convertView = LayoutInflater.from(getContext()).inflate(viewTypes.get(type), null);
        }

        //set the TextView UI to display the sent text, timestamp and date
        TextView messageTextView = (TextView) convertView.findViewById(R.id.text_message);
        TextView timestampTextView = (TextView) convertView.findViewById(R.id.text_timestamp);
        //TextView dateTextView = (TextView) convertView.findViewById(R.id.text_date);
        if (messageTextView != null) {
            messageTextView.setText(message.getMessageText());
            timestampTextView.setText(message.getTimeStamp());
            //dateTextView.setText(message.getDate());
        }

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {

        SignalMessage message = getItem(position);
        return message.isRemote() ? VIEW_TYPE_REMOTE : VIEW_TYPE_LOCAL;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypes.size();
    }
}
