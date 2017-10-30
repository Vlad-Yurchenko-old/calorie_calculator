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

import com.example.homeinc.caloriecalculator.MyContextApplication;
import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.Product;

public class AddMenuItem extends DialogFragment {

    private Product product;

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View linearLayout = inflater.inflate(R.layout.fragment_add_in_menu, null);
        builder.setView(linearLayout);

        final EditText numOfGramms = (EditText) linearLayout.findViewById(R.id.numOfGramms);

        builder.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MyContextApplication.calculatorFragment.addProduct(product, Integer.parseInt(numOfGramms.getText().toString()));
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
