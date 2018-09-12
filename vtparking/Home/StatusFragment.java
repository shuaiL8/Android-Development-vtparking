package com.example.fredliu.vtparking.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fredliu.vtparking.R;

/**
 * Created by fredliu on 12/7/17.
 */

public class StatusFragment extends Fragment {

    Button checkOut;
    FragmentTransaction fragmentTransaction;

    public StatusFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        checkOut = (Button) view.findViewById(R.id.checkOut);


        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                MapFragment mapFragment = new MapFragment();
                fragmentTransaction.replace(R.id.homeContent, mapFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
