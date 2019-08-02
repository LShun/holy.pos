package order;

import staff.Worker;

import java.util.ArrayList;

public abstract class CartOrReceipt {
    protected String billID;
    protected Worker worker;
    protected static int transactionMade = 0;
    protected ArrayList<Item> listOfItems = new ArrayList<Item>();

    public String getBillID()                 { return billID; }
    public Worker getWorker()                  { return worker; }
    public ArrayList<Item> getListOfItems(){ return listOfItems; }

    abstract public void display();

}
