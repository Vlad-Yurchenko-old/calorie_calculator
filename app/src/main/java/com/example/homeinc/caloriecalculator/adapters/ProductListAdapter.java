package com.example.homeinc.caloriecalculator.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.Product;

import java.util.ArrayList;

public class ProductListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> recipes;

    public ProductListAdapter(Context context, ArrayList<Product> recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    public ArrayList<Product> getRecipes() {
        return recipes;
    }

    public void setRecipes(ArrayList<Product> recipes) {
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_list_products ,null);
        ((TextView) v.findViewById(R.id.item_list_products_name)).setText(recipes.get(position).getName());
        ((TextView) v.findViewById(R.id.item_list_products_kkal)).setText(String.valueOf(recipes.get(position).getKkal())  + " (ккал.)");
        ((TextView) v.findViewById(R.id.item_list_products_bju)).setText(String.valueOf(recipes.get(position).getProteins())
                                                                            + "; " + String.valueOf(recipes.get(position).getFats())
                                                                            + "; " + String.valueOf(recipes.get(position).getCarbohydrates()) + " (БЖУ)");

        v.setTag(recipes.get(position).getId());
        return v;
    }

}
