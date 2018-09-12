package com.example.fredliu.vtparking.Notification;


import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.example.fredliu.vtparking.Discover.ParkingSpot;
import com.example.fredliu.vtparking.Discover.SpotAdapter;
import com.example.fredliu.vtparking.R;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    Button refresh;
    ListView notificationListView;
    ToggleButton toggleButton;
    public NotificationsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);


        refresh = (Button) view.findViewById(R.id.notificationRefreshButton);
        toggleButton = (ToggleButton) view.findViewById(R.id.toggleButton);
        notificationListView = (ListView) view.findViewById(R.id.notificationListView);

        SpotAdapter spotAdapter = new SpotAdapter(getActivity(), R.layout.spot_reserve, getData());

        notificationListView.setAdapter(spotAdapter);

        notificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
        });

        return view;
    }

    private ArrayList<ParkingSpot> getData() {
        final ArrayList<ParkingSpot> parkingSpot = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.Image_parkingSpot);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            parkingSpot.add(new ParkingSpot(bitmap,
                    getResources().getStringArray(R.array.ParkingSpot)[i],
                    getResources().getStringArray(R.array.ParkingSpot)[i],
                    getResources().getStringArray(R.array.ParkingSpot)[i]));
        }
        return parkingSpot;
    }

    public interface OnFragmentInteractionListener {
    }

}
