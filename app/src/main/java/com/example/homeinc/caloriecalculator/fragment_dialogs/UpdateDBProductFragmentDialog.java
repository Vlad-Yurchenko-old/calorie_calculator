package com.example.homeinc.caloriecalculator.fragment_dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.Product;
import com.example.homeinc.caloriecalculator.sqlite.ProductDao;
import com.example.homeinc.caloriecalculator.tabLayout.fragments.ListFragment;

import java.util.ArrayList;

public class UpdateDBProductFragmentDialog extends DialogFragment {

    private Product product;
    private ArrayList<Product> products;
    private ListFragment listFragment;

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setListFragment(ListFragment listFragment) {
        this.listFragment = listFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.fragment_update_dbproduct, null);
        builder.setView(view);

        final EditText name = (EditText) view.findViewById(R.id.update_edit_product_name);
        final EditText kkal = (EditText) view.findViewById(R.id.update_edit_product_kkal);
        final EditText prot = (EditText) view.findViewById(R.id.update_edit_product_prot);
        final EditText fats = (EditText) view.findViewById(R.id.update_edit_product_fats);
        final EditText carbon = (EditText) view.findViewById(R.id.update_edit_product_carbon);

        name.setText(product.getName());
        kkal.setText(String.valueOf(product.getKkal()));
        prot.setText(String.valueOf(product.getProteins()));
        fats.setText(String.valueOf(product.getFats()));
        carbon.setText(String.valueOf(product.getCarbohydrates()));

        builder.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        product.setName(name.getText().toString());
                        product.setKkal(Double.parseDouble(kkal.getText().toString()));
                        product.setProteins(Double.parseDouble(prot.getText().toString()));
                        product.setFats(Double.parseDouble(fats.getText().toString()));
                        product.setCarbohydrates(Double.parseDouble(carbon.getText().toString()));
                        new ProductDao(getActivity().getApplicationContext()).update(product);

                        products.set(products.indexOf(product), product);
                        listFragment.updateList(products);

                        dialog.dismiss();
                    }
                });
        builder.setNegativeButton("ОТМЕНА",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return builder.create();
    }

}
