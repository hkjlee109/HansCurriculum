package com.kwoolytech.step04;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class CommonTool {

    public static String temperatureUnit = "C";
    public final static Double DEFAULTLAT = 37.49;
    public final static Double DEFAULTLNG = 127.01;

    public final static String WEATHERQUERYURL = "http://api.openweathermap.org/data/2.5/weather?";
    public final static String APIKEY = "&APPID=7c9bbeb8ddd8520e0d7a72bf796ff493";
    public final static String WEATHERICONURL = "http://openweathermap.org/img/w/";

    public final static int CODE_MAP_REQUEST = 1001;
    public final static int CODE_SETTINGS_REQUEST = 1101;
    public final static int CODE_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 3001;

    public static String getCommonTemperature(int celsius) {
        int ret;

        if (temperatureUnit.equalsIgnoreCase("C")) {
            ret = celsius;
        }
        else {
            ret = (9/5) * celsius + 32;
        }

        return Integer.toString(ret);
    }

    public static LatLng getCurrentLocationOrElse(Context context, LatLng defaultLocation) {
        LatLng currentLocation = defaultLocation;
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        try {
            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
                Location gps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                currentLocation = new LatLng(gps.getLatitude(), gps.getLongitude());
            }
        } catch (Exception e) {
            Log.e(context.getClass().getName(), "GPS is unavailable.");
        }

        return currentLocation;
    }

}
