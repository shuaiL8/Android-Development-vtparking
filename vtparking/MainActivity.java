package com.example.fredliu.vtparking;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.fredliu.vtparking.Camera.CameraFragment;
import com.example.fredliu.vtparking.Discover.DiscoverFragment;
import com.example.fredliu.vtparking.Discover.ParkingLotFragment;
import com.example.fredliu.vtparking.Home.HomeFragment;
import com.example.fredliu.vtparking.Home.MapFragment;
import com.example.fredliu.vtparking.Home.StatusFragment;
import com.example.fredliu.vtparking.Me.MeFragment;
import com.example.fredliu.vtparking.Notification.NotificationsFragment;
import com.example.fredliu.vtparking.Service.BackgroundService;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.OnFragmentInteractionListener,
        DiscoverFragment.OnFragmentInteractionListener,
        NotificationsFragment.OnFragmentInteractionListener,
        MeFragment.OnFragmentInteractionListener,
        ParkingLotFragment.OnFragmentInteractionListener,
        CameraFragment.OnFragmentInteractionListener,
        StatusFragment.OnFragmentInteractionListener,
        MapFragment.OnMapReadyCallBack,
        MapFragment.LocationListener,
        MapFragment.OnMyLocationButtonClickListener {

    FragmentTransaction fragmentTransaction;
    BackgroundService service;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, new HomeFragment());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_discover:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, new DiscoverFragment());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_notifications:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, new NotificationsFragment());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_Me:
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content, new MeFragment());
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(RetainFragment.getInstance(getFragmentManager()).popData());
        setContentView(R.layout.activity_main);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.content, new HomeFragment());
        fragmentTransaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        Intent startBackServiceIntent;
        startBackServiceIntent = new Intent(this, BackgroundService.class);
        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                service = ((BackgroundService.LocalBinder)iBinder).getService();
            }
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
        bindService(startBackServiceIntent, connection, BIND_AUTO_CREATE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.camera) {

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, new CameraFragment());
            fragmentTransaction.commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            String scannedData = result.getContents();
            if (scannedData != null) {
                Toast.makeText(MainActivity.this,scannedData,Toast.LENGTH_LONG).show();
                Log.d("cs3714", "result: " + scannedData);
            } else {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        RetainFragment.getInstance( getFragmentManager() ).pushData( (Bundle) outState.clone() );
        outState.clear(); // We don't want a TransactionTooLargeException, so we handle things via the SavedInstanceFragment
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( RetainFragment.getInstance( getFragmentManager() ).popData() );
    }

}
