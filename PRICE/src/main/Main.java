package main;

import pricelist.*;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", new PriceList.Price(4, 2));
        priceList.addProduct(2,"orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1,"brick", new PriceList.Price(200));
        System.out.println(priceList.setName(1, "jopa"));
        System.out.println(priceList);
    }
}
