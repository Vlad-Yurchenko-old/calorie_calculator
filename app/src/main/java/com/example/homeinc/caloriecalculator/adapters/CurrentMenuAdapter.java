package com.example.homeinc.caloriecalculator.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.CurrentMenuItem;

import java.util.ArrayList;

public class CurrentMenuAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<CurrentMenuItem> products;

    public CurrentMenuAdapter(Context context, ArrayList<CurrentMenuItem> products) {
        this.context = context;
        this.products = products;
    }

    public ArrayList<CurrentMenuItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<CurrentMenuItem> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_current_menu_product ,null);

                ((TextView) v.findViewById(R.id.item_current_menu_product_name)).setText(products.get(position).product.getName());
        ((TextView) v.findViewById(R.id.item_current_menu_product_kkal)).setText(String.valueOf(products.get(position).product.getKkal()));
        //((TextView) v.findViewById(R.id.item_current_menu_product_kkal)).setText(String.valueOf(products.get(position).product.getKkal()) + " (ккал) - " + products.get(position).number + " г.");
        ((TextView) v.findViewById(R.id.item_current_menu_product_bju)).setText(String.valueOf(products.get(position).product.getProteins())
                + "; " + String.valueOf(products.get(position).product.getFats())
                + "; " + String.valueOf(products.get(position).product.getCarbohydrates()) + " (БЖУ)");

        v.setTag(products.get(position).product.getId());
        return v;
    }
}
