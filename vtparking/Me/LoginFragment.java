package com.example.fredliu.vtparking.Me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fredliu.vtparking.R;

/**
 * Created by fredliu on 12/7/17.
 */

public class LoginFragment extends Fragment{

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragemnt_login, container, false);

        return view;
    }

    public interface OnFragmentInteractionListener {
    }
}
