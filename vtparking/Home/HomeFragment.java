package com.example.fredliu.vtparking.Home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fredliu.vtparking.R;


public class HomeFragment extends Fragment {

    ImageView vtLogo;
    Button switchButton;
    FragmentTransaction fragmentTransaction;
    boolean status = true;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        vtLogo = (ImageView) view.findViewById(R.id.vtLogo);

        vtLogo.setBackgroundResource(R.drawable.vtlogo);

        if (status) {

            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            StatusFragment statusFragment = new StatusFragment();
            fragmentTransaction.replace(R.id.homeContent, statusFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
        if (status == false) {

            fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            MapFragment mapFragment = new MapFragment();
            fragmentTransaction.replace(R.id.homeContent, mapFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

//        switchButton = (Button) view.findViewById(R.id.switchButton);
//
//
//        switchButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                StatusFragment statusFragment = new StatusFragment();
//                MapFragment mapFragment = new MapFragment();
//
//                if (switchButton.getText() == "STATUS") {
//                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.homeContent, statusFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                    switchButton.setText("MAP");
//                }
//                if (switchButton.getText() == "MAP") {
//                    fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.homeContent, mapFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//                    switchButton.setText("STATUS");
//
//                }
//            }
//        });


        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
