package pricelist;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PriceList {
    public static class Price {
        private int ruble;
        private int penny;

        public Price(int ruble, int penny) {
            this.ruble = ruble;
            this.penny = penny;
        }

        public int getRuble() {
            return ruble;
        }

        public int getPenny() {
            return penny;
        }

        public void setRuble(int newRuble) {
            ruble = newRuble;
        }

        public void setPenny(int newPenny) {
            penny = newPenny;
        }

        @Override
        public String toString() {

            return super.toString();
        }
    }

    public static class Product {
        private String name;
        private Price price;

        public Product(String name, Price price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Price getPrice() {
            return price;
        }

        public void setName(String newName) {
            name = newName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return price.ruble == product.price.ruble &&
                    price.penny == product.price.penny &&
                    name.equals(product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ");
            sb.append(name);
            sb.append(", Price: ");
            sb.append(price.ruble);
            sb.append(" rub., ");
            sb.append(price.penny);
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

    private boolean checkId(Integer id) {
        return productList.keySet().contains(id);
    }

    public void addProduct(Integer id, Product newProduct) {
        if (!checkId(id)) productList.put(id, newProduct);
        else throw new IllegalArgumentException();
    }

    public void addProduct(Integer id, String name, Price price) {
        if (price.ruble > -1 && price.penny > -1 && price.penny < 100)
            addProduct(id, new Product(name, price));
        else throw new IllegalArgumentException();
    }

    public void setName(Integer id, String newName) {
        if (checkId(id)) productList.get(id).name = newName;
        else throw new IllegalArgumentException();
    }

    public void setPrice(Integer id, Price newPrice) {
        if (checkId(id)) productList.get(id).price = newPrice;
        else throw new IllegalArgumentException();
    }

    public double getPrice(Integer id, int cont) {
        if (checkId(id)) {
            int findRuble = productList.get(id).getPrice().ruble * cont;
            int findPenny = productList.get(id).getPrice().penny * cont;
            if (findPenny > 100) {
                findRuble = findPenny / 100;
                findPenny = findPenny % 100;
            }
            double findSum = findRuble + findPenny * 0.01;
            return findSum;
        }
        else throw new IllegalArgumentException();
    }

    public double getPrice(Integer id) {
        return getPrice(id, 1);
    }

    public String getName(Integer id) {
        if (checkId(id)) return productList.get(id).name;
        else throw new IllegalArgumentException();
    }

    public Product removeProduct(Integer id) {
        if (checkId(id)) {
            Product deletedProduct = productList.remove(id);
            return deletedProduct;
        } else throw new IllegalArgumentException();
    }

    public int getId(String name) {
        for (Map.Entry<Integer, Product> entry : productList.entrySet()) {
            if (entry.getValue().name.equals(name))
                return entry.getKey();
        }
        return -1;
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
        for (Map.Entry<Integer, Product> entry : productList.entrySet()) {
            sb.append("{ID: ");
            sb.append(entry.getKey());
            sb.append(", ");
            sb.append(entry.getValue());
            sb.append("}\n");
        }
        return sb.append("}").toString();
    }
}