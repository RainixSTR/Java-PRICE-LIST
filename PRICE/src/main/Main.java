package main;

import pricelist.*;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", new PriceList.Price(4, 2));
        priceList.addProduct(2,"orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList();
        System.out.println(priceList);
        priceList1.addProduct(1,"brick", new PriceList.Price(200));
        priceList.removeProduct(1);
        System.out.println(priceList);
    }
}
