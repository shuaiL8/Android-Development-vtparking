package com.example.fredliu.vtparking.Discover;


import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;

import com.example.fredliu.vtparking.R;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment{

    SearchView searchView;
    GridView gridView;
    GridViewAdapter gridAdapter;
    FragmentTransaction fragmentTransaction;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);


        searchView = (SearchView) view.findViewById(R.id.searchView);
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(getActivity(), R.layout.parking_lot_image, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);




                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("title", item.getTitle());
                bundle.putParcelable("image", item.getImage());
                bundle.putInt("position", position);
                ParkingLotFragment parkingLotFragment = new ParkingLotFragment();
                parkingLotFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.content, parkingLotFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.Image_parkingLot);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, getResources().getStringArray(R.array.ParkingLot)[i]));
        }
        return imageItems;
    }

    public interface OnFragmentInteractionListener {
    }

}
