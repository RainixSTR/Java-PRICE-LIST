package pricelist;

import java.util.*;

public class PriceList {
    public static class Price {
        private int ruble;
        private int penny;

        public Price(int ruble, int penny) {
            if (ruble > -1 && penny > -1 && penny < 100) {
                this.ruble = ruble;
                this.penny = penny;
            } else throw new IllegalArgumentException();
        }

        public Price(int penny) {
            if (penny > -1) {
                this.ruble = penny / 100;
                this.penny = penny % 100;
            } else throw new IllegalArgumentException();
        }

        public int getPriceInPenny() {
            return ruble * 100 + penny;
        }

        public int getRuble() {
            return ruble;
        }

        public int getPenny() {
            return penny;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Price price = (Price) o;
            return ruble == price.ruble &&
                    penny == price.penny;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ruble, penny);
        }

        @Override
        public String toString() {
            return "Price: " + ruble +
                    " rub., " + penny +
                    " pen.";
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

        public void setPrice(Price newPrice) {
            price = newPrice;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(name, product.name) &&
                    Objects.equals(price, product.price);
        }
    }

    private Map<Integer, Product> productList = new HashMap<>();

    public PriceList(Map<Integer, Product> list) {
        productList.putAll(list);
    }

    public PriceList() {
    }

    public void addProduct(Integer id, Product newProduct) {
        if (productList.putIfAbsent(id, newProduct) != null)
            throw new IllegalArgumentException();
    }

    public void addProduct(Integer id, String name, Price price) {
        addProduct(id, new Product(name, price));
    }

    public void setName(Integer id, String newName) {
        Product product = productList.get(id);
        if (product == null) throw new IllegalArgumentException();
        else product.setName(newName);
    }

    public void setPrice(Integer id, Price newPrice) {
        Product product = productList.get(id);
        if (product == null) throw new IllegalArgumentException();
        else product.setPrice(newPrice);
    }

    public Price getPrice(Integer id, int cont) {
        Product product = productList.get(id);
        if (product != null) {
            int sumPenny = product.getPrice().getPriceInPenny() * cont;
            return new Price(sumPenny);
        } else throw new IllegalArgumentException();
    }

    public Price getPrice(Integer id) {
        return getPrice(id, 1);
    }

    public String getName(Integer id) {
        Product product = productList.get(id);
        if (product != null) return product.name;
        else throw new IllegalArgumentException();
    }

    public boolean removeProduct(Integer id) {
        return productList.remove(id) != null;
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