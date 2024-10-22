package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> items; // final 은 setter 없음

    public ShoppingCart() {
        items = new ArrayList<Product>();
    }

    public void addItem(Product item){
        items.add(item);
    }

    public List<Product> getItems() {
        return items;
    }
}
