package order;

import food_menu.Product;
import staff.Staff;
import java.util.HashMap;

public abstract class CartOrReceipt {
    protected String billID;
    protected Staff staff;
    protected static int transactionMade = 0;
    protected HashMap<Product, Integer> items = new HashMap<Product , Integer>();

    public String getBillID()                 { return billID; }
    public Staff getStaff()                  { return staff; }
    public HashMap<Product , Integer> getItems(){ return items; }

    abstract public void display();

}
