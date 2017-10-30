package com.example.homeinc.caloriecalculator.tabLayout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homeinc.caloriecalculator.MyContextApplication;
import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.adapters.CurrentMenuAdapter;
import com.example.homeinc.caloriecalculator.domain.CurrentMenuItem;
import com.example.homeinc.caloriecalculator.domain.Product;
import com.example.homeinc.caloriecalculator.fragment_dialogs.UpdateMenuItem;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment {

    private static final int LAYOUT = R.layout.fragment_calculator;
    private static final int MENU = R.menu.context_current_menu_product;

    private Context context;
    private CurrentMenuAdapter adapter;

    private View view;
    private FloatingActionButton fab;
    private ListView listView;
    private TextView currentSum;

    private static ArrayList<CurrentMenuItem> products;


    public void addProduct(Product product, int number){
        CurrentMenuItem menuItem = new CurrentMenuItem(product, number);
        products.add(menuItem);
        updateCaculator(products);
        calculateCurrentSum();
    }

    public void clearCurrentMenu(){
        products.clear();
        updateCaculator(products);
        calculateCurrentSum();
    }

    public void updateProduct(CurrentMenuItem item){
        for(CurrentMenuItem curItem: products){
            if(curItem.product.getName() == item.product.getName() && curItem.product.getKkal() == item.product.getKkal()){
                curItem = item;
            }
        }
        updateCaculator(products);
        calculateCurrentSum();
    }

    public void removeProduct(CurrentMenuItem item){
        products.remove(item);
        updateCaculator(products);
        calculateCurrentSum();
    }

    public void removeProduct(int id){
        products.remove(id);
        updateCaculator(products);
        calculateCurrentSum();
    }

    public void calculateCurrentSum(){
        int sum = 0;
        for(CurrentMenuItem temp: products){
            sum += temp.product.getKkal()*temp.number/100;
        }
        currentSum.setText("Итого : " + sum + " ккал.");
    }

    public void updateCaculator(ArrayList<CurrentMenuItem> productsTemp){
        products = productsTemp;
        adapter.setProducts(products);
        adapter.notifyDataSetChanged();
        calculateCurrentSum();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
        setHasOptionsMenu(true);//?
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.listViewCalculator);
        registerForContextMenu(listView);

        products = new ArrayList<>();
        adapter = new CurrentMenuAdapter(context, products);

        currentSum = (TextView) view.findViewById(R.id.currentSum);

        fab = (FloatingActionButton) view.findViewById(R.id.fabCurrrentMenu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyContextApplication.viewPager.setCurrentItem(1);
                Toast.makeText(getActivity().getApplicationContext(), "Выберите продукт", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: выбрать конкретно метод выпадение контекстного меню
                getActivity().openContextMenu(view);
            }
        });

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(MENU, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {

            case R.id.context_current_menu_item_update:
                Toast.makeText(context, "Еще в разработке", Toast.LENGTH_SHORT).show();
                UpdateMenuItem updateMenuItem = new UpdateMenuItem();
                updateMenuItem.setProduct(products.get(info.position));
                updateMenuItem.show(getFragmentManager(), "custom");
                return true;

            case R.id.context_current_menu_item_delete:
                removeProduct(info.position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


}
