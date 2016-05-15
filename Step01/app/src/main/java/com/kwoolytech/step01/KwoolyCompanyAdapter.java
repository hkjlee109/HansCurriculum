package com.kwoolytech.step01;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kwooly on 5/15/16.
 */
public class KwoolyCompanyAdapter extends BaseAdapter {
    ArrayList<String> list;
    Context context;
    int itemLayout;

    KwoolyCompanyAdapter(Context context, int itemLayout, ArrayList<String> list){
        this.context = context;
        this.itemLayout = itemLayout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(itemLayout, parent, false);

            TextView textView = (TextView)convertView.findViewById(R.id.textView);
            textView.setText(list.get(position));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DescriptionActivity.class);
                    intent.putExtra("Company", list.get(pos));
                    context.startActivity(intent);
                }
            });

            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);
            BitmapDrawable bitmapDrawable = (BitmapDrawable)ContextCompat.getDrawable(context, GetCompanyResourceId(list.get(position)));
            imageView.setImageDrawable(bitmapDrawable);

            Button button = (Button)convertView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
        return convertView;
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
}
