package com.example.brian.stawika.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.brian.stawika.R;
import com.example.brian.stawika.model.RatesArrayObject;

import java.util.ArrayList;

public class RatesAdapter extends BaseAdapter implements SpinnerAdapter {
  private ArrayList<RatesArrayObject> ratesArrayObjects;
  private Context context;

  public RatesAdapter(ArrayList<RatesArrayObject> ratesArrayObjects, Context context) {
    this.ratesArrayObjects = ratesArrayObjects;
    this.context = context;
  }

  @Override
  public int getCount() {
    return ratesArrayObjects.size();
  }

  @Override
  public Object getItem(int position) {
    return ratesArrayObjects.get(position);
  }

  @Override
  public long getItemId(int position) {
    return ratesArrayObjects.get(position).getProductId();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = View.inflate(context, R.layout.custom_spinner, null);
    TextView textView = view.findViewById(R.id.main);
    RatesArrayObject object = ratesArrayObjects.get(position);
    textView.setText(object.getProduct());
    return textView;
  }

  @Override
  public View getDropDownView(int position, View convertView, ViewGroup parent) {

    View view;
    view = View.inflate(context, R.layout.custom_dropdown, null);
    TextView textView = view.findViewById(R.id.dropdown);
    textView.setText(ratesArrayObjects.get(position).getProduct());
    return view;
  }
}

