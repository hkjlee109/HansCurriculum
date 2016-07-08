package com.kwoolytech.step03;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface KwoolyHttpCallback {
    void getJsonDataCallback (String result);
    void getBitmapDataCallback (Bitmap result);
}

public class KwoolyHttp {
    private Context mainContext;

    KwoolyHttp(Context context) {
        mainContext = context;
    }

    public void httpGetJsonData(String url, KwoolyHttpCallback callback) throws IOException {
        if(networkIsConnected()) {
            new HttpGetJsonDataTask(callback).execute(url);
        }
        return;
    }

    public void httpGetBitmapData(String url, KwoolyHttpCallback callback) throws IOException {
        if(networkIsConnected()) {
            new HttpGetBitmapDataTask(callback).execute(url);
        }
        return;
    }

    private boolean networkIsConnected() {
        ConnectivityManager connectivity = (ConnectivityManager)mainContext.getSystemService(
                                               mainContext.CONNECTIVITY_SERVICE);

        NetworkInfo network = connectivity.getActiveNetworkInfo();
        return (network != null && network.isConnected());
    }

    private class HttpGetJsonDataTask extends AsyncTask<String, Void, String> {
        final KwoolyHttpCallback callback;

        HttpGetJsonDataTask(KwoolyHttpCallback callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return startHttpGetJsonDataTransaction(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            callback.getJsonDataCallback(result);
        }
    }

    private class HttpGetBitmapDataTask extends AsyncTask<String, Void, Bitmap> {
        final KwoolyHttpCallback callback;

        HttpGetBitmapDataTask(KwoolyHttpCallback callback) {
            this.callback = callback;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                return startHttpGetBitmapDataTransaction(urls[0]);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            callback.getBitmapDataCallback(result);
        }
    }

    private String startHttpGetJsonDataTransaction(String url) throws IOException {
        StringBuilder result = new StringBuilder();

        try {
            URL requestUrl = new URL(url);
            if(requestUrl == null)
                return null;

            HttpURLConnection httpConnection = (HttpURLConnection)requestUrl.openConnection();
            if(httpConnection == null)
                return null;

            httpConnection.setReadTimeout(10000);
            httpConnection.setConnectTimeout(10000);
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoInput(true);
            httpConnection.setDoOutput(true);

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            }

            httpConnection.disconnect();
        } catch (Exception e) {
            Log.e("KwoolyHttp", "Exception: Unable to connect.");
            e.printStackTrace();
        }

        return result.toString();
    }

    private Bitmap startHttpGetBitmapDataTransaction(String url) throws IOException {
        Bitmap result = null;

        try {
            URL requestUrl = new URL(url);
            if(requestUrl == null)
                return null;

            HttpURLConnection httpConnection = (HttpURLConnection)requestUrl.openConnection();
            if(httpConnection == null)
                return null;

            httpConnection.setReadTimeout(10000);
            httpConnection.setConnectTimeout(10000);
            httpConnection.setRequestMethod("GET");
            httpConnection.setDoInput(true);

            if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = BitmapFactory.decodeStream(httpConnection.getInputStream());
            }

            httpConnection.disconnect();
        } catch (Exception e) {
            Log.e("KwoolyHttp", "Exception: Unable to connect.");
            e.printStackTrace();
        }

        return result;
    }
}
