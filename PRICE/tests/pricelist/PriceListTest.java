package pricelist;

import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class PriceListTest {
    @Test
    public void addProduct() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", 4, 2);
        priceList.addProduct(2,"orange", 3, 2);
        PriceList priceList1 = new PriceList(new HashMap<Integer, PriceList.Product>() {{
            put(2, new PriceList.Product("orange", 3, 2));
            put(1, new PriceList.Product("apple", 4, 2));
        }});
        assertEquals(priceList, priceList1);
        priceList.addProduct(3,"banana", 3, 34);
        assertNotEquals(priceList, priceList1);
        assertThrows(IllegalArgumentException.class, ()-> {priceList.addProduct(1,"potato", 4, 2);});
    }

    @Test
    public void setName() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", 4, 2);
        priceList.addProduct(2,"orange", 3, 2);
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1,"brick", 4, 2);
        priceList1.addProduct(2,"orange", 3, 2);
        assertNotEquals(priceList, priceList1);
        priceList.setName(1, "brick");
        assertEquals(priceList, priceList1);

    }

    @Test
    public void setPrice() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", 4, 2);
        priceList.addProduct(2,"orange", 3, 2);
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1,"apple", 5, 9);
        priceList1.addProduct(2,"orange", 3, 2);
        assertNotEquals(priceList, priceList1);
        priceList.setPrice(1, 5, 9);
        assertEquals(priceList, priceList1);
    }

    @Test
    public void removeProduct() {
        PriceList priceList = new PriceList();
        priceList.addProduct(1,"apple", 4, 2);
        priceList.addProduct(2,"orange", 3, 2);
        PriceList priceList1 = new PriceList();
        priceList1.addProduct(1,"apple", 4, 2);
        assertNotEquals(priceList, priceList1);
        priceList.removeProduct(2);
        assertEquals(priceList, priceList1);
    }
}