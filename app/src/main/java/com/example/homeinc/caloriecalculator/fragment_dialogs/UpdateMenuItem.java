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
import android.widget.TextView;

import com.example.homeinc.caloriecalculator.MyContextApplication;
import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.CurrentMenuItem;

public class UpdateMenuItem extends DialogFragment {

    private CurrentMenuItem product;

    public void setProduct(CurrentMenuItem product) {
        this.product = product;
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

        View view = inflater.inflate(R.layout.fragment_update_current_menu_product, null);
        builder.setView(view);

        final TextView name = (TextView) view.findViewById(R.id.current_item_name);
        final EditText number = (EditText) view.findViewById(R.id.current_item_number);

        name.setText(product.product.getName());
        number.setText(String.valueOf(product.number));

        builder.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        product.number = Integer.valueOf(number.getText().toString());
                        MyContextApplication.calculatorFragment.updateProduct(product);
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
