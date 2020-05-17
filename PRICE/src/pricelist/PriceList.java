package pricelist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceList {

    public static class Product {
        private String name;
        private int ruble;
        private int penny;

        public Product(String name, int ruble, int penny) {
            this.name = name;
            this.ruble = ruble;
            this.penny = penny;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return ruble == product.ruble &&
                    penny == product.penny &&
                    name.equals(product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, ruble, penny);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ");
            sb.append(name);
            sb.append(", Price: ");
            sb.append(ruble);
            sb.append(" rub., ");
            sb.append(penny);
            sb.append(" pen.");
            return sb.toString();
        }
    }

    private Map<Integer, Product> productList = new HashMap<>();

    public PriceList(Map<Integer, Product> list) {
        productList.putAll(list);
    }

    public PriceList() {
    }

    public void addProduct(Integer id, Product newProduct) {
        if (!checkId(id)) productList.put(id, newProduct);
        else throw new IllegalArgumentException();
    }

    public void addProduct(Integer id, String name, int ruble, int penny) {
        addProduct(id, new Product(name, ruble, penny));
    }

    public void setName(Integer id, String newName) {
        productList.get(id).name = newName;
    }

    public void setPrice(Integer id, int newRuble, int newPenny) {
        productList.get(id).ruble = newRuble;
        productList.get(id).penny = newPenny;
    }

    public void removeProduct(Integer id) {
        productList.remove(id);
    }

    public String getPrice(Integer id, int count) {
        int pennySum = productList.get(id).penny * count;
        int rubleSum = productList.get(id).ruble * count;
        while (pennySum > 99) {
            rubleSum += 1;
            pennySum -= 100;
        }

        return (rubleSum + "." + pennySum);
    }

    public String getPrice(Integer id) {
        return getPrice(id, 1);
    }

    public int getId(String name) {
        for (Map.Entry<Integer, Product> entry : productList.entrySet()) {
            if (entry.getValue().name.equals(name))
                return entry.getKey();
        }
        return -1;
    }

    private boolean checkId(Integer id) {
        return productList.keySet().contains(id);
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
