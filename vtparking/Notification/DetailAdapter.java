package com.example.fredliu.vtparking.Notification;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fredliu.vtparking.R;

import java.util.ArrayList;

/**
 * Created by fredliu on 12/7/17.
 */

public class DetailAdapter extends ArrayAdapter<Detail> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Detail> data = new ArrayList<Detail>();

    public DetailAdapter(Context context, int layoutResourceId, ArrayList<Detail> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.content = (TextView) row.findViewById(R.id.content);
            holder.time = (TextView) row.findViewById(R.id.time);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        Detail item = data.get(position);
        holder.content.setText(item.getContent());
        holder.time.setText(item.getTime());
        return row;
    }

    class ViewHolder {
        TextView content;
        TextView time;
    }
}
