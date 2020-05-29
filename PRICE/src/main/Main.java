package main;

import pricelist.*;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", new PriceList.Price(4, 2));
        priceList.addProduct(2,"orange", new PriceList.Price(3, 2));
        System.out.println(priceList);
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1,"brick", new PriceList.Price(4, 2));
        priceList1.addProduct(2,"orange", new PriceList.Price(3, 2));
        priceList.setName(1, "apple");
        System.out.println(priceList.getPrice(1));
    }
}
