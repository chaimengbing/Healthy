package com.health.infrared.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * BaseAdapter
 */
public abstract class Base<T> extends android.widget.BaseAdapter {
    public List<T> list;
    public List<T> lists;
    public Context context;
    public LayoutInflater inflater;

    public Base() {
    }

    public Base(Context context, List<T> list) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public Base(List<T> list, List<T> lists, Context context) {
        this.list = list;
        this.lists = lists;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null && !list.isEmpty() ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    public abstract View createView(int position, View convertView, ViewGroup parent);

    public void setData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}

