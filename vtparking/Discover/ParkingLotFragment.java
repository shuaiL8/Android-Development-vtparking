package com.example.fredliu.vtparking.Discover;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fredliu.vtparking.Home.MapFragment;
import com.example.fredliu.vtparking.R;

import java.util.ArrayList;

public class ParkingLotFragment extends Fragment {

    TextView parkingLotTextView;
    ListView parkingLotListView;
    ImageView parkingLotImageView;
    Button parkingLotRefreshButton;
    ImageButton parkingLotImageButton;
    FragmentTransaction fragmentTransaction;

    public ParkingLotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parking_lot, container, false);

        parkingLotTextView = (TextView) view.findViewById(R.id.parkingLotTextView);
        parkingLotListView = (ListView) view.findViewById(R.id.parkingLotListView);
        parkingLotImageView = (ImageView) view.findViewById(R.id.parkingLotImageView);
        parkingLotRefreshButton = (Button) view.findViewById(R.id.parkingLotRefreshButton);
        parkingLotImageButton = (ImageButton) view.findViewById(R.id.parkingLotImageButton);


        String title = getArguments().getString("title");
        Bitmap bitmap = getArguments().getParcelable("image");
        final int position = getArguments().getInt("position");

        parkingLotTextView.setText(title);
        parkingLotImageView.setImageBitmap(bitmap);

        SpotAdapter spotAdapter = new SpotAdapter(getActivity(), R.layout.spot_reserve, getData());

        parkingLotListView.setAdapter(spotAdapter);
        setListViewHeightBasedOnChildren(parkingLotListView);


        parkingLotImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("title", parkingLotTextView.getText().toString());
                bundle.putInt("position", position);
                MapFragment mapFragment = new MapFragment();
                mapFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, mapFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        parkingLotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



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

    public class MyListView extends ListView {

        public MyListView(Context context) {
            super(context);
        }

        public MyListView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public MyListView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        }

    }

    public void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

    }
}
