package order;

import staff.Staff;
import java.util.ArrayList;

public abstract class CartOrReceipt {
    protected String billID;
    protected Staff staff;
    protected static int transactionMade = 0;
    protected ArrayList<Item> listOfItems = new ArrayList<Item>();

    public String getBillID()                 { return billID; }
    public Staff getStaff()                  { return staff; }
    public ArrayList<Item> getListOfItems(){ return listOfItems; }

    abstract public void display();

}
