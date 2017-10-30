package com.example.homeinc.caloriecalculator.domain;

public class Product {

    private int id;

    private String name;        //название продукта
    private double kkal;           //ккал
    private double proteins;       //белки
    private double fats;           //жиры
    private double carbohydrates;  //углеводы

    public Product() {
    }

    public Product(String name, double kkal, double proteins, double fats, double carbohydrates) {
        this.name = name;
        this.kkal = kkal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public Product(int id, String name, double kkal, double proteins, double fats, double carbohydrates) {
        this.id = id;
        this.name = name;
        this.kkal = kkal;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKkal() {
        return kkal;
    }

    public void setKkal(double kkal) {
        this.kkal = kkal;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kkal=" + kkal +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
