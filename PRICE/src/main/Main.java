package main;

import pricelist.*;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", 4, 3);
        PriceList priceList1 = new PriceList(new HashMap<Integer, PriceList.Product>() {{
            put(1, new PriceList.Product("1", 0, 1));
            put(2, new PriceList.Product("2", 0, 3));
        }});
        PriceList priceList3 = new PriceList();
        priceList3.addProduct(1,"apple", 4, 2);
        priceList3.addProduct(2,"orange", 3, 2);
        PriceList priceList4 = new PriceList(new HashMap<Integer, PriceList.Product>() {{
            put(1, new PriceList.Product("apple", 4, 2));
            put(2, new PriceList.Product("orange", 3, 2));
        }});
        System.out.println(priceList);
        System.out.println(priceList1);
        System.out.println(priceList.getPrice(1));
        System.out.println(priceList.getPrice(1, 5));
        System.out.println(priceList3.hashCode());
        System.out.println(priceList4.hashCode());
    }
}
