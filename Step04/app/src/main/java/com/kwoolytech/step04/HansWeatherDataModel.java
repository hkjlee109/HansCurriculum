package com.kwoolytech.step04;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

public class HansWeatherDataModel {
    private JSONObject weatherData;
    private Bitmap     weatherBitmap;

    public HansWeatherDataModel() {}

    public void setWeatherJsonData(String result) throws JSONException {
        weatherData = new JSONObject(result);
        return;
    }

    public void setWeatherBitmapData(Bitmap result) throws JSONException {
        weatherBitmap = result;
        return;
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

    public String getDatetime() throws JSONException {
        if (weatherData == null)
            return null;

        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        return dateFormat.format(new Date(weatherData.getLong("dt")*1000));
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
}