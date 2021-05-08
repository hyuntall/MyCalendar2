package com.android.calendar2;

import android.app.ActionBar;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private int mResource;
    private ArrayList<MonthCalendarAdapter> mItems = new ArrayList<MonthCalendarAdapter>();
    public MyAdapter(Context context, int resource, ArrayList<MonthCalendarAdapter> items){
        mContext = context;
        mItems = items;
        mResource = resource;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
