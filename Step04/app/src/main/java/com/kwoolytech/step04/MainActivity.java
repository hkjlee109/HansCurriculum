package com.kwoolytech.step04;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private HansWeatherDataModel   dataModel;
    private KwoolyHttp             httpClient;
    private KwoolyHttpCallback     httpClientCallback;

    private HansWeatherViewAdapter listViewWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModel = new HansWeatherDataModel();
        httpClient  = new KwoolyHttp(MainActivity.this);

        initializeUi();
        initializeKwoolyHttpCallbackFunction();

        queryWeatherJsonData();
        return;
    }

    private void initializeUi() {
        listViewWeatherAdapter = new HansWeatherViewAdapter(MainActivity.this, dataModel,
                                         R.layout.listviewitem_weather);
        ((ListView)findViewById(R.id.listViewWeather)).setAdapter(listViewWeatherAdapter);

        findViewById(R.id.buttonRefresh).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryWeatherJsonData();
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

    private void queryWeatherJsonData() {
        try {
            httpClient.httpGetJsonData(
                    CommonTool.WeatherQueryUrl + "lat=37.49&lon=127.01&units=metric" + CommonTool.ApiKey,
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
                    CommonTool.WeatherIconUrl + dataModel.getIconCode() + ".png",
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
}
