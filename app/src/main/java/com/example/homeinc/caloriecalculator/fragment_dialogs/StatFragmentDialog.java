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

import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.domain.CurrentMenuItem;
import com.example.homeinc.caloriecalculator.sqlite.MenuProductDao;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StatFragmentDialog extends DialogFragment {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View linearLayout = inflater.inflate(R.layout.fragment_stat, null);
        builder.setView(linearLayout);


        GraphView graph = (GraphView) linearLayout.findViewById(R.id.graph);
        ArrayList<DataPoint> dataPoints = new ArrayList<>();
        ArrayList<CurrentMenuItem> currentMenuItems = new MenuProductDao(getContext()).readAll();

        ArrayList<CurrentMenuItem> currentMenuItemsYet = new ArrayList<>();
        for (CurrentMenuItem currentMenuItem : currentMenuItems) {
            if (!currentMenuItemsYet.contains(currentMenuItem)) {
                currentMenuItemsYet.add(currentMenuItem);

            }

            Date dt = null;
            try {
                dt = dateFormat.parse(currentMenuItem.date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            double date = (double) dt.getTime();

            DataPoint dp = null;
            for (DataPoint dpTemp : dataPoints) {
                if (dpTemp.getX() == date) {
                    dp = dpTemp;
                    break;
                }
            }
            if (dp == null) {
                dp = new DataPoint(date, currentMenuItem.number * currentMenuItem.product.getKkal() / 100);
                dataPoints.add(dp);
            } else {
                DataPoint temp = new DataPoint(date, dp.getY() + (currentMenuItem.number * currentMenuItem.product.getKkal() / 100));
                dataPoints.set(dataPoints.indexOf(dp), temp);
            }
        }

        DataPoint[] dps = new DataPoint[dataPoints.size()];
        for (int i = 0; i < dataPoints.size(); i++) {
            dps[i] = dataPoints.get(i);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dps);
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));

        graph.getGridLabelRenderer().setNumHorizontalLabels(3);

        graph.getGridLabelRenderer().setHumanRounding(false);

        builder.setPositiveButton("ОК",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
