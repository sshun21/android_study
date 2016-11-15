package org.androidtown.lbs.map;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.LinkedList;


public class MainActivity extends ActionBarActivity {

    private GoogleMap map;

    static LinkedList<LatLng> list = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        startLocationService();
    }
    public void onResume(){
        super.onResume();
        map.setMyLocationEnabled(true);
    }

    public void onPause(){
        super.onPause();
        map.setMyLocationEnabled(false);
    }

    private void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        GPSListener gpsListener = new GPSListener();
        long minTime = 10000;
        float minDistance = 0;

        manager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime,
                minDistance,
                gpsListener);

        manager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime,
                minDistance,
                gpsListener);

        Toast.makeText(getApplicationContext(), "위치 확인 시작함. 로그를 확인하세요.", Toast.LENGTH_SHORT).show();
    }

    private class GPSListener implements LocationListener {



        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String msg = " 위도 : "+ latitude + "  경도:"+ longitude;
            Log.i("현재 위치는 <", msg+" >");

            Toast.makeText(getApplicationContext(), msg,Toast.LENGTH_SHORT).show();

            LatLng point = new LatLng(latitude,longitude);

            map.addCircle(new CircleOptions().center(point).radius(1));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 17));
            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            if(list.size()>0){
//                map.addPolyline(new PolylineOptions().add(list.get(list.size()-1),list.get(list.size())).width(5).color(Color.RED));
//            }
            if(list.size()>0){
                map.addPolyline(new PolylineOptions().add(point,list.getLast()).width(5).color(Color.RED));
            }
            list.add(point);
        }



        public void onProviderDisabled(String provider) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
