package com.example.fredliu.vtparking.Discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fredliu.vtparking.R;

import java.util.ArrayList;

/**
 * Created by fredliu on 12/3/17.
 */

public class SpotAdapter extends ArrayAdapter<ParkingSpot> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ParkingSpot> data = new ArrayList<ParkingSpot>();

    public SpotAdapter(Context context, int layoutResourceId, ArrayList<ParkingSpot> data) {
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
            holder.lot = (TextView) row.findViewById(R.id.lot);
            holder.spot = (TextView) row.findViewById(R.id.spot);
            holder.floor = (TextView) row.findViewById(R.id.floor);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }


        ParkingSpot item = data.get(position);
        holder.lot.setText(item.getLot());
        holder.spot.setText(item.getSpot());
        holder.floor.setText(item.getFloor());
        holder.image.setImageBitmap(item.getImage());
        return row;
    }

    class ViewHolder {
        TextView lot;
        TextView spot;
        TextView floor;
        ImageView image;
    }

}
