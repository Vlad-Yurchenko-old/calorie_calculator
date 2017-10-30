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
import com.example.homeinc.caloriecalculator.adapters.ProductListAdapter;
import com.example.homeinc.caloriecalculator.domain.Product;
import com.example.homeinc.caloriecalculator.sqlite.ProductDao;

public class AddNewProductFragmentDialog extends DialogFragment{

    private ProductDao productDao;
    private ProductListAdapter productListAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        productDao = new ProductDao(getActivity().getApplicationContext());
    }

    public void setAdapter(ProductListAdapter productListAdapter){
        this.productListAdapter = productListAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View linearLayout = inflater.inflate(R.layout.fragment_add_new_product, null);
        builder.setView(linearLayout);

        final EditText name = (EditText) linearLayout.findViewById(R.id.edit_product_name);
        final EditText kkal = (EditText) linearLayout.findViewById(R.id.edit_product_kkal);
        final EditText prot = (EditText) linearLayout.findViewById(R.id.edit_product_prot);
        final EditText fats = (EditText) linearLayout.findViewById(R.id.edit_product_fats);
        final EditText carbon = (EditText) linearLayout.findViewById(R.id.edit_product_carbon);

        builder.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Product product = new Product();
                        product.setName(name.getText().toString());
                        product.setKkal(Integer.parseInt(kkal.getText().toString()));
                        product.setProteins(Integer.parseInt(prot.getText().toString()));
                        product.setFats(Integer.parseInt(fats.getText().toString()));
                        product.setCarbohydrates(Integer.parseInt(carbon.getText().toString()));

                        productDao.create(product);

                        productListAdapter.setRecipes(productDao.readAll());
                        productListAdapter.notifyDataSetChanged();
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
