package com.example.homeinc.caloriecalculator.tabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.homeinc.caloriecalculator.MyContextApplication;
import com.example.homeinc.caloriecalculator.tabLayout.fragments.CalculatorFragment;
import com.example.homeinc.caloriecalculator.tabLayout.fragments.ListFragment;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter{

    public String tabs[];

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{"Калькулятор",
                "Список продуктов"};
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                CalculatorFragment calculatorFragment = new CalculatorFragment();
                MyContextApplication.calculatorFragment = calculatorFragment;
                return calculatorFragment;

            case 1:
                return new ListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
