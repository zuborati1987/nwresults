package com.codecool.model;

public class Q5pojo {
    private String company;
    private String product;
    private int price;

    public Q5pojo(String company, String product, int price) {
        this.company = company;
        this.product = product;
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }
}
