package com.kwoolytech.step04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng location = CommonTool.getCurrentLocationOrElse(this, new LatLng(CommonTool.DEFAULTLAT,
                                                                               CommonTool.DEFAULTLNG));

        map.addMarker(new MarkerOptions().position(location).draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(location));

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng location) {
                map.clear();
                map.addMarker(new MarkerOptions().position(location).draggable(true));
                map.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("Lat", marker.getPosition().latitude);
                intent.putExtra("Lng", marker.getPosition().longitude);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                setResult(Activity.RESULT_OK, intent);

                finish();
                return true;
            }
        });
    }
}
