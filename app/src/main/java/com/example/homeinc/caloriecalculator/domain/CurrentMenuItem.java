package com.example.homeinc.caloriecalculator.domain;


public class CurrentMenuItem {
    public Product product;
    public int number;
    public String date;

    public CurrentMenuItem(){
    }

    public CurrentMenuItem(Product product, String date, int number) {
        this.product = product;
        this.number = number;
        this.date = date;
    }

}
