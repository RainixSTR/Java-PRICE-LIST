package pricelist;

import org.junit.Test;

import java.util.HashMap;
import static org.junit.Assert.*;

public class PriceListTest {
    @Test
    public void addProduct() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList(new HashMap() {{
            put(2, new PriceList.Product("orange", new PriceList.Price(3, 2)));
            put(1, new PriceList.Product("apple", new PriceList.Price(4, 2)));
        }});
        assertEquals(priceList, priceList1);
    }

    @Test
    public void setName() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1, "brick", new PriceList.Price(4, 2));
        priceList1.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertNotEquals(priceList, priceList1);
        priceList1.setName(1, "apple");
        assertEquals(priceList, priceList1);

    }

    @Test
    public void setPrice() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1, "apple", new PriceList.Price(5, 9));
        priceList1.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertNotEquals(priceList, priceList1);
        priceList.setPrice(1, new PriceList.Price(5, 9));
        assertEquals(priceList, priceList1);
    }

    @Test
    public void removeProduct() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1, "apple", new PriceList.Price(4, 2));
        assertNotEquals(priceList, priceList1);
        priceList.removeProduct(2);
        assertEquals(priceList, priceList1);
    }

    @Test
    public void getPrice() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertEquals(new PriceList.Price(4,2), priceList.getPrice(1));
    }

    @Test
    public void getSumPrice() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertEquals(new PriceList.Price(151,0), priceList.getPrice(2,50));
    }

    @Test
    public void getName() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertEquals("orange", priceList.getName(2));
    }

    @Test
    public void getId() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1, "apple", new PriceList.Price(4, 2));
        priceList.addProduct(2, "orange", new PriceList.Price(3, 2));
        assertEquals(1, priceList.getId("apple"));
    }
}