package com.example.homeinc.caloriecalculator.tabLayout.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.homeinc.caloriecalculator.MyContextApplication;
import com.example.homeinc.caloriecalculator.R;
import com.example.homeinc.caloriecalculator.adapters.ProductListAdapter;
import com.example.homeinc.caloriecalculator.domain.Product;
import com.example.homeinc.caloriecalculator.fragment_dialogs.AddMenuItem;
import com.example.homeinc.caloriecalculator.fragment_dialogs.AddNewProductFragmentDialog;
import com.example.homeinc.caloriecalculator.fragment_dialogs.UpdateDBProductFragmentDialog;
import com.example.homeinc.caloriecalculator.sqlite.ProductDao;

import java.util.ArrayList;

public class ListFragment extends Fragment{

    private static final int LAYOUT = R.layout.fragment_list;
    private Context context;
    private View view;

    private ArrayList<Product> products;

    private FloatingActionButton fab;
    private ListView listView;
    private ProductListAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        listView = (ListView) view.findViewById(R.id.listViewList);
        registerForContextMenu(listView);

        products = new ProductDao(context).readAll();

        adapter = new ProductListAdapter(context, products);

        fab = (FloatingActionButton) view.findViewById(R.id.fabList);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewProductFragmentDialog dialog = new AddNewProductFragmentDialog();
                dialog.setAdapter(adapter);
                dialog.show(getFragmentManager(),"custom");

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_settings);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Product> tempProducts = new ArrayList<Product>();
                for(Product product: products){
                    if(product.getName().toLowerCase().contains(newText.toLowerCase())){
                        tempProducts.add(product);
                    }
                }
                adapter.setRecipes(tempProducts);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu_product, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        products = adapter.getRecipes();
        switch (item.getItemId()) {

            case R.id.context_menu_add:
                AddMenuItem addMenuItem = new AddMenuItem();
                addMenuItem.setProduct(products.get(info.position));
                addMenuItem.show(getFragmentManager(),"custom");

                MyContextApplication.viewPager.setCurrentItem(0);
                return true;

            case R.id.context_menu_update_product_from_base:
                UpdateDBProductFragmentDialog dialog = new UpdateDBProductFragmentDialog();
                dialog.setProduct(products.get(info.position));
                dialog.setProducts(products);
                dialog.setListFragment(this);
                dialog.show(getFragmentManager(), "custom");
                return true;

            case R.id.context_menu_delete_product_from_base:
                Product deletedProduct = products.get(info.position);
                new ProductDao(context).delete(deletedProduct.getId());
                products.remove(deletedProduct);
                adapter.setRecipes(products);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void updateList(ArrayList<Product> products){
        this.products = products;
        adapter.setRecipes(products);
        adapter.notifyDataSetChanged();
    }


}
