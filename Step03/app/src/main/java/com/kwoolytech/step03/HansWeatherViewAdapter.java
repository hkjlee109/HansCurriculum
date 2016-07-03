package com.kwoolytech.step03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONException;

import java.util.ArrayList;

public class HansWeatherViewAdapter extends BaseAdapter {
    private HansWeatherDataModel mainDataModel;
    private ArrayList<String>    itemList;
    private int                  itemLayout;
    private LayoutInflater       inflater;

    public class ViewHolder {
        TextView textData;
    }

    HansWeatherViewAdapter(Context context, HansWeatherDataModel dataModel,
                           int layout, ArrayList<String> list) {
        mainDataModel = dataModel;
        itemLayout = layout;
        itemList = list;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
            return itemList.size();
        }

    @Override
    public String getItem(int position) { return itemList.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = inflater.inflate(itemLayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textData = (TextView) view.findViewById(R.id.textData);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        try {
            switch (position) {
                case 0:
                    viewHolder.textData.setText(mainDataModel.getCity() + ", " + mainDataModel.getCountry());
                    break;
                case 1:
                    viewHolder.textData.setText(
                            Integer.toString(mainDataModel.getMinTemperature()) + " " +
                            Integer.toString(mainDataModel.getAverageTemperature()) + " " +
                            Integer.toString(mainDataModel.getMaxTemperature()));
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}