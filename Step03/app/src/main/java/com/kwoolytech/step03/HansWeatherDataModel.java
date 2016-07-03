package com.kwoolytech.step03;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class HansWeatherDataModel {
    private Context            mainContext;
    private KwoolyHttp         httpClient;
    private KwoolyHttpCallback httpClientCallback;
    private JSONObject         weatherData;

    HansWeatherDataModel(Context context) {
        mainContext = context;
        httpClient  = new KwoolyHttp(mainContext);
        weatherData = new JSONObject();

        httpClientCallback = new KwoolyHttpCallback() {
            @Override
            public void getJsonDataCallback(String result) {
                try {
                    weatherData = new JSONObject(result);
                    ((ListView)((Activity) mainContext).findViewById(R.id.listViewWeather)).invalidateViews();
                } catch (JSONException e) {
                    Log.e("HansWeatherDataModel", "Exception: Unable to get JSONObject.");
                    e.printStackTrace();
                }
            }
        };

        UpdateData();
    }

    public String getCountry() throws JSONException {
        if (weatherData == null)
            return null;

        return (weatherData.getJSONObject("sys")).getString("country");
    }

    public String getCity() throws JSONException {
        if (weatherData == null)
            return null;

        return weatherData.getString("name");
    }

    public int getAverageTemperature() throws JSONException {
        if (weatherData == null)
            return 0;

        return (weatherData.getJSONObject("main")).getInt("temp");
    }

    public int getMinTemperature() throws JSONException {
        if (weatherData == null)
            return 0;

        return (weatherData.getJSONObject("main")).getInt("temp_min");
    }

    public int getMaxTemperature() throws JSONException {
        if (weatherData == null)
            return 0;

        return (weatherData.getJSONObject("main")).getInt("temp_max");
    }

    private void UpdateData() {
        try {
            httpClient.httpGetJsonData(
                           CommonTool.WeatherQueryUrl + "lat=37.49&lon=127.01" + CommonTool.ApiKey,
                           httpClientCallback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
