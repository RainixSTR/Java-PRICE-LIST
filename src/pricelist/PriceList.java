package pricelist;

import java.util.Arrays;
import java.util.List;

public class PriceList {

    public class Product {
        private final long id;
        private String name;
        private double price;

        private Product(long id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }
    }

    private List<Product> productList;

    public void addProduct(Product newProduct) {
        productList.add(newProduct);
    }

    public void addProduct(long id, String name, double price) {
        productList.add(new Product(id, name, price));
    }


}
