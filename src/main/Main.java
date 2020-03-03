package main;

import pricelist.*;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PriceList priceList = new PriceList();
        priceList.addProduct("hello", 4.2);
        PriceList priceList1 = new PriceList(new HashMap<Integer, PriceList.Product>() {{
            put(1, new PriceList.Product("1", 0));
            put(2, new PriceList.Product("2", 0));
        }});
        System.out.println(priceList);
        System.out.println(priceList1);

    }
}
