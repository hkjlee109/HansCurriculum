package com.kwoolytech.step04;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HansWeatherViewAdapter extends BaseAdapter {
    private Context              mainContext;
    private HansWeatherDataModel mainDataModel;
    private int                  itemLayout;
    private LayoutInflater       inflater;

    public class ViewHolder {
        TextView  textLocation;
        TextView  textMinTemperature;
        TextView  textAverageTemperature;
        TextView  textMaxTemperature;
        ImageView imageWeather;
        TextView  textDatetime;
    }

    public HansWeatherViewAdapter(Context context, HansWeatherDataModel dataModel, int layout) {
        mainContext   = context;
        mainDataModel = dataModel;
        itemLayout    = layout;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
            return 1;
        }

    @Override
    public String getItem(int position) { return null; }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = inflater.inflate(itemLayout, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.textLocation = (TextView) view.findViewById(R.id.textLocation);
            viewHolder.textMinTemperature = (TextView) view.findViewById(R.id.textMinTemperature);
            viewHolder.textAverageTemperature = (TextView) view.findViewById(R.id.textAverageTemperature);
            viewHolder.textMaxTemperature = (TextView) view.findViewById(R.id.textMaxTemperature);
            viewHolder.imageWeather = (ImageView) view.findViewById(R.id.imageWeather);
            viewHolder.textDatetime = (TextView) view.findViewById(R.id.textDatetime);

            BitmapDrawable bitmapDrawable = (BitmapDrawable) ContextCompat
                                                .getDrawable(mainContext, R.drawable.location);
            ((ImageView)view.findViewById(R.id.imageLocation)).setImageDrawable(bitmapDrawable);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        try {
            viewHolder.textLocation.setText(mainDataModel.getCity() + ", " + mainDataModel.getCountry());
            viewHolder.textMinTemperature.setText(CommonTool.getCommonTemperature(mainDataModel.getMinTemperature()));
            viewHolder.textAverageTemperature.setText(CommonTool.getCommonTemperature(mainDataModel.getAverageTemperature()));
            viewHolder.textMaxTemperature.setText(CommonTool.getCommonTemperature(mainDataModel.getMaxTemperature()));
            viewHolder.imageWeather.setImageBitmap(mainDataModel.getWeatherBitmap());
            viewHolder.textDatetime.setText(mainDataModel.getDatetime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}