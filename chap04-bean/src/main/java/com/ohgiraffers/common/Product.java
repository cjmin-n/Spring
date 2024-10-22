package com.ohgiraffers.common;

public abstract class Product {

    // 추상클래스의 장점 : 코드의 중복이 줄어든다.

    private String name; // 상품명
    private int price; // 가격

    public Product() {
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " " + price;
    }
}
