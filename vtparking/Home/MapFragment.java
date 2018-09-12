package com.example.fredliu.vtparking.Home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fredliu.vtparking.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by fredliu on 12/8/17.
 */

public class MapFragment extends android.support.v4.app.Fragment {
    MapView mMapView;
    private GoogleMap googleMap;
    int position;
    String title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            position = getArguments().getInt("position");
            title = getArguments().getString("title");
        }
        else {
            position = 0;
            title = "VT Parking Lot";

        }

        final double longitude2 = Double.parseDouble(getResources().getStringArray(R.array.ParkingLotLongitude)[position]);
        final double latitude2 = Double.parseDouble(getResources().getStringArray(R.array.ParkingLotLatitude)[position]);;

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);


                // For dropping a marker at a point on the Map
                LatLng Location = new LatLng(longitude2, latitude2);
                //LatLng Location = new LatLng(x, y);
                googleMap.addMarker(new MarkerOptions().position(Location).title(title).snippet("Spot Available").draggable(true));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(Location).zoom(17).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {
                        Log.e("mapDrag", "DragStart : " + marker.getPosition());
                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {
                        Log.e("mapDrag", "Drag : " + marker.getPosition());
                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {
                        Log.e("mapDrag", "DragEnd : " + marker.getPosition());
                        double longitude3 = marker.getPosition().longitude;
                        double latitude3 = marker.getPosition().latitude;
                        // For dropping a marker at a point on the Map
                        LatLng Location = new LatLng(longitude3, latitude3);
                        //LatLng Location = new LatLng(x, y);
                        googleMap.addMarker(new MarkerOptions().position(Location).title(title).snippet("Spot Available").draggable(true));
                    }
                });
            }
        });


        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public interface OnMapReadyCallBack {
    }

    public interface LocationListener {
    }

    public interface OnMyLocationButtonClickListener {
    }
}