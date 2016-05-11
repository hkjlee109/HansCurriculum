package com.kwoolytech.step01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class DescriptionActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Intent intent = getIntent();

        DisplayCompany(GetCompanyResourceId(intent.getStringExtra("Company")));
    }

    private int GetCompanyResourceId(String company) {
        int resourceId = 0;

        switch(company) {
            case "Dasan Networks":
                resourceId = R.drawable.dasannetworks;
                break;
            case "SK Telecom":
                resourceId = R.drawable.sktelecom;
                break;
            case "GE Appliances":
                resourceId = R.drawable.geappliances;
                break;
            default:
                resourceId = R.drawable.auckland;
                break;
        }

        return resourceId;
    }

    private void DisplayCompany(int resourceId) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable)ContextCompat.getDrawable(this, resourceId);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageDrawable(bitmapDrawable);
    }
}
