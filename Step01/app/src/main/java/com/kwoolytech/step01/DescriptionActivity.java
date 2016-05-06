package com.kwoolytech.step01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

public class DescriptionActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        int resourceId = 0;
        Intent intent = getIntent();
        String company = intent.getStringExtra("Company");

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
        }

        BitmapDrawable bitmapDrawable = (BitmapDrawable)ContextCompat.getDrawable(this, resourceId);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
    }
}
