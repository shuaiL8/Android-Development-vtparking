package com.example.fredliu.vtparking.Me;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fredliu.vtparking.Notification.Detail;
import com.example.fredliu.vtparking.Notification.DetailAdapter;
import com.example.fredliu.vtparking.R;

import java.util.ArrayList;

/**
 * Created by fredliu on 12/7/17.
 */

public class HistoryFragment extends Fragment{

    ListView historyListView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        historyListView = (ListView) view.findViewById(R.id.historyListView);

        DetailAdapter detailAdapter = new DetailAdapter(getActivity(), R.layout.fragment_history_detail, getData());

        historyListView.setAdapter(detailAdapter);

        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {



            }
        });

        return view;
    }

    private ArrayList<Detail> getData() {
        final ArrayList<Detail> detail = new ArrayList<>();
        TypedArray l = getResources().obtainTypedArray(R.array.History);
        for (int i = 0; i < l.length(); i++) {
            detail.add(new Detail(
                    getResources().getStringArray(R.array.History)[i],
                    getResources().getStringArray(R.array.History)[i]));
        }
        return detail;
    }

    public interface OnFragmentInteractionListener {
    }
}
