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

    private List<Integer> idList = new ArrayList<>();

    private boolean checkIdInList(Integer id) {
        return idList.contains(id);
    }

    public PriceList() {
    }

    public void addProduct(Integer id, Product newProduct) {
        if (!checkIdInList(id)) {
            productList.put(id, newProduct);
            idList.add(id);
        } else throw new IllegalArgumentException();
    }

    public void addProduct(Integer id, String name, Price price) {
        addProduct(id, new Product(name, price));
    }

    public boolean setName(Integer id, String newName) {
        if (checkIdInList(id)) {
            productList.get(id).name = newName;
            return true;
        }
        return false;
    }

    public boolean setPrice(Integer id, Price newPrice) {
        if (checkIdInList(id)) {
            productList.get(id).price = newPrice;
            return true;
        }
        return false;
    }

    public Price getPrice(Integer id, int cont) {
        if (checkIdInList(id)) {
            int sumPenny = productList.get(id).getPrice().getPriceInPenny() * cont;
            Price sumPrice = new Price(sumPenny);
            return sumPrice;
        } else throw new IllegalArgumentException();
    }

    public Price getPrice(Integer id) {
        return getPrice(id, 1);
    }

    public String getName(Integer id) {
        if (checkIdInList(id)) return productList.get(id).name;
        else throw new IllegalArgumentException();
    }

    public void removeProduct(Integer id) {
        if (checkIdInList(id)) {
            productList.remove(id);
            idList.remove(id);
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