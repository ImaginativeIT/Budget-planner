package com.application.budgetplanner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.application.budgetplanner.R;

public class IncomeAdapter extends BaseAdapter {

    private Context context;
    private int singleItemLayout;
    String[] array = {"a","b","c","d","e","f","g"};

    public IncomeAdapter(Context c, int singleItemLayout){
        context = c;
        this.singleItemLayout = singleItemLayout;

    }


    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(singleItemLayout, viewGroup, false);
    }
}
