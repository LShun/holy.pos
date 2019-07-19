package food_menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    private Product p = new Product("BBEEF", "Beefburger",	"Beefburger Deluxe", 5.99, 0.16);
    private Product q = new Product("BC", "Cheeseburger", "Cheeseburger", 5.99, 0.16);
    private final double THREEDP = 0.001;
    private final int EXPECTHASH = -1413175596;

    @Test
    public void getTitle() {
        assertEquals("Beefburger", p.getTitle());
    }

    @Test
    public void getDesc() {
        assertEquals("Beefburger Deluxe", p.getDesc());
    }

    @Test
    public void getPrice() {
        assertEquals(5.99, p.getPrice(), THREEDP);
    }

    @Test
    public void getId() {
        assertEquals("BBEEF", p.getId());
    }

    @Test
    public void getTax() {
        assertEquals(0.16, p.getTax(), THREEDP);
    }

    @Test
    public void hashCode1() {
        assertEquals(EXPECTHASH, p.hashCode());
    }

    @Test
    public void equals1() {
        assertEquals(false, p.equals(q));
        assertEquals(true, p.equals(p));
        assertEquals(false, p.equals(null));
    }
}