package com.example.brian.stawika.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.brian.stawika.model.SpinnerArrayObject;
import com.example.brian.stawika.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter implements SpinnerAdapter {
    private ArrayList<SpinnerArrayObject> spinnerArrayObjects;
    private Context context;

    public CustomAdapter(Context context, ArrayList<SpinnerArrayObject> spinnerArrayObjects) {
        this.spinnerArrayObjects = spinnerArrayObjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return spinnerArrayObjects.size();
    }

    @Override
    public SpinnerArrayObject getItem(int position) {
        return spinnerArrayObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return
                spinnerArrayObjects.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.company_main, null);
        TextView textView = view.findViewById(R.id.main);
        SpinnerArrayObject currentObject = spinnerArrayObjects.get(position);
        textView.setText(currentObject.getName());
        return textView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        View view;
        view = View.inflate(context, R.layout.company_dropdown, null);
        TextView textView = view.findViewById(R.id.dropdown);
        textView.setText(spinnerArrayObjects.get(position).getName());
        return view;
    }
}
