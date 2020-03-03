package pricelist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceList {

    public static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("NAME: ");
            sb.append(name);
            sb.append(", PRICE: ");
            sb.append(price);
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Double.compare(product.price, price) == 0 &&
                    Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }
    }

    private Map<Integer, Product> productList = new HashMap<>();
    private int lastID = 0;

    public PriceList(Map<Integer, Product> list) {
       productList.putAll(list);
       int maxID = 0;
       for (Integer id: productList.keySet()) {
           if (maxID < id) maxID = id;
       }
       lastID = maxID + 1;
    }

    public PriceList() {
    }

    public void addProduct(Product newProduct) {
        productList.put(lastID + 1, newProduct);
        lastID++;
    }

    public void addProduct(String name, double price) {
        addProduct(new Product(name, price));
    }

    public void setName(Integer id, String newName) {
        productList.get(id).name = newName;
    }

    public void setPrice(Integer id, double newPrice) {
        productList.get(id).price = newPrice;
    }

    public void removeProduct(Integer id) {
        productList.remove(id);
    }

    public double getPrice(Integer id, int count) {
        return productList.get(id).price * count;
    }

    public double getPrice(Integer id) {
        return getPrice(id, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof PriceList)) return false;
        PriceList other = (PriceList) o;
        return this.productList.equals(other.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Price List (SIZE = ");
        sb.append(productList.size());
        sb.append(") {\n");
        for (Map.Entry<Integer, Product> entry: productList.entrySet()) {
            sb.append("{ID: ");
            sb.append(entry.getKey());
            sb.append(", ");
            sb.append(entry.getValue());
            sb.append("}\n");
        }
        return sb.append("}").toString();
    }
}
