package com.kwoolytech.step01;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class KwoolyCompanyAdapter extends BaseAdapter {
    private ArrayList<String> list;
    private Context context;
    private int itemLayout;
    private LayoutInflater inflater;

    public class ViewHolder {
        TextView textView;
        ImageView imageView;
        Button button;
    }

    KwoolyCompanyAdapter(Context context, int itemLayout, ArrayList<String> list){
        this.context = context;
        this.itemLayout = itemLayout;
        this.list = list;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        View view = inflater.inflate(itemLayout, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.textView = (TextView)view.findViewById(R.id.textView);
        viewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);
        viewHolder.button = (Button)view.findViewById(R.id.button);

        viewHolder.textView.setText(list.get(pos));
        BitmapDrawable bitmapDrawable = (BitmapDrawable)ContextCompat
                                            .getDrawable(context, IntentTool.GetCompanyResourceId(list.get(pos)));
        viewHolder.imageView.setImageDrawable(bitmapDrawable);
        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                                           Uri.parse(IntentTool.GetCompanyHomepageURL(list.get(pos))));
                context.startActivity(intent);
            }
        });

        /* Alternative way to define a default onClick handler.
        convertView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("Company", list.get(pos));
                context.startActivity(intent);
            }
        });
        */

        return view;
    }
}
