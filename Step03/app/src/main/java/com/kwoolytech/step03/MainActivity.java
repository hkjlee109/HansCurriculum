package com.kwoolytech.step03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private HansWeatherDataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModel = new HansWeatherDataModel(MainActivity.this);

        HansWeatherViewAdapter adapter = new HansWeatherViewAdapter(MainActivity.this, dataModel,
                                                 R.layout.listviewitem_weather);

        ((ListView)findViewById(R.id.listViewWeather)).setAdapter(adapter);

        findViewById(R.id.buttonRefresh).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ListView)findViewById(R.id.listViewWeather)).invalidateViews();
            }
        });
    }
}
