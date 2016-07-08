package com.kwoolytech.step03;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

public class HansWeatherDataModel {
    private Context            mainContext;
    private KwoolyHttp         httpClient;
    private KwoolyHttpCallback httpClientCallback;
    private JSONObject         weatherData;
    private Bitmap             weatherBitmap;

    HansWeatherDataModel(Context context) {
        mainContext = context;
        httpClient  = new KwoolyHttp(mainContext);

        httpClientCallback = new KwoolyHttpCallback() {
            @Override
            public void getJsonDataCallback(String result) {
                try {
                    weatherData = new JSONObject(result);
                    httpClient.httpGetBitmapData(
                                   "http://openweathermap.org/img/w/" + getIconCode() + ".png",
                                   httpClientCallback);
                } catch (Exception e) {
                    Log.e("HansWeatherDataModel", "Exception: Unable to handle getJsonDataCallback.");
                    e.printStackTrace();
                }
            }

            @Override
            public void getBitmapDataCallback(Bitmap result) {
                weatherBitmap = result;
                ((ListView)((Activity) mainContext).findViewById(R.id.listViewWeather)).invalidateViews();
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

    public String getIconCode() throws JSONException {
        if (weatherData == null)
            return null;

        return (weatherData.getJSONArray("weather").getJSONObject(0)).getString("icon");
    }

    public Bitmap getWeatherBitmap() throws JSONException {
        if (weatherBitmap == null)
            return null;

        return weatherBitmap;
    }

    private void UpdateData() {
        try {
            httpClient.httpGetJsonData(
                           CommonTool.WeatherQueryUrl + "lat=37.49&lon=127.01&units=metric" + CommonTool.ApiKey,
                           httpClientCallback);
        } catch (Exception e) {
            Log.e("HansWeatherDataModel", "Exception: Unable to UpdateData.");
            e.printStackTrace();
        }
    }
}