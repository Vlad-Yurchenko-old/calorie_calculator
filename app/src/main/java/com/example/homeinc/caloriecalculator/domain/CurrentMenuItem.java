package com.example.homeinc.caloriecalculator.domain;


public class CurrentMenuItem {
    public Product product;
    public int number;

    public CurrentMenuItem(Product product, int number) {
        this.product = product;
        this.number = number;
    }

    public CurrentMenuItem(){

    }
}
