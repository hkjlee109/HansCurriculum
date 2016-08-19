package com.kwoolytech.step04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private HansWeatherDataModel   dataModel;
    private KwoolyHttp             httpClient;
    private KwoolyHttpCallback     httpClientCallback;

    private HansWeatherViewAdapter listViewWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        dataModel   = new HansWeatherDataModel();
        httpClient  = new KwoolyHttp(MainActivity.this);

        initializeUi();
        initializeKwoolyHttpCallbackFunction();

        queryWeatherJsonData(37.49, 127.01);

        return;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CommonTool.CODE_PROCESS_MAP_LOCATION_REQUEST) {
            if (resultCode == RESULT_OK) {
                queryWeatherJsonData(data.getDoubleExtra("Lat", 0), data.getDoubleExtra("Lng", 0));
            }
        }
    }

    private void initializeUi() {
        listViewWeatherAdapter = new HansWeatherViewAdapter(MainActivity.this, dataModel, R.layout.listviewitem_weather);
        ((ListView)findViewById(R.id.listViewWeather)).setAdapter(listViewWeatherAdapter);

        findViewById(R.id.buttonRefresh).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryWeatherJsonData(37.49, 127.01);
            }
        });
        return;
    }

    private void initializeKwoolyHttpCallbackFunction() {
        httpClientCallback = new KwoolyHttpCallback() {
            @Override
            public void onWeatherJsonDataReceived(String result) {
                try {
                    dataModel.setWeatherJsonData(result);
                    presentData();
                    queryWeatherBitmapData();
                } catch (Exception e) {
                    Log.e(getClass().getName(), "Exception: ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onWeatherBitmapDataReceived(Bitmap result) {
                try {
                    dataModel.setWeatherBitmapData(result);
                    presentData();
                } catch (Exception e) {
                    Log.e(getClass().getName(), "Exception: ");
                    e.printStackTrace();
                }
            }
        };
    }

    private void queryWeatherJsonData(double lat, double lng) {
        try {
            httpClient.httpGetJsonData(
                    CommonTool.WEATHERQUERYURL + "lat=" + lat + "&lon=" + lng + "&units=metric" + CommonTool.APIKEY,
                    httpClientCallback);
        } catch (Exception e) {
            Log.e(getClass().getName(), "Exception: ");
            e.printStackTrace();
        }
        return;
    }

    private void queryWeatherBitmapData() {
        try {
            httpClient.httpGetBitmapData(
                    CommonTool.WEATHERICONURL + dataModel.getIconCode() + ".png",
                    httpClientCallback);
        } catch (Exception e) {
            Log.e(getClass().getName(), "Exception: ");
            e.printStackTrace();
        }
        return;
    }

    private void presentData() {
        listViewWeatherAdapter.notifyDataSetChanged();
        return;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivityForResult(intent, CommonTool.CODE_PROCESS_MAP_LOCATION_REQUEST);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
