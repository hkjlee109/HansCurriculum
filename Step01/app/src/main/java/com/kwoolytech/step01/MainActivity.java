package com.kwoolytech.step01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = new ArrayList<String>();
        list.add("Dasan Networks");
        list.add("SK Telecom");
        list.add("GE Appliances");
        list.add("Auckland-01");
        list.add("Auckland-02");
        list.add("Auckland-03");
        list.add("Auckland-04");
        list.add("Auckland-05");
        list.add("Auckland-06");

        KwoolyCompanyAdapter adapter = new KwoolyCompanyAdapter(this, R.layout.listviewitem_company, list);

        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DescriptionActivity.class);
                intent.putExtra("Company", (String)listView.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        return;
    }
}
