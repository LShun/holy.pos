package order;

import staff.Worker;

import java.util.ArrayList;

public abstract class CartOrReceipt {
    protected static int transactionMade = 0;
    protected String billID;
    protected Worker worker;
    protected ArrayList<Item> listOfItems = new ArrayList<Item>();

    public String getBillID() {
        return billID;
    }

    public Worker getWorker() {
        return worker;
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    abstract public void display();

    @Override
    public String toString() {
        return "CartOrReceipt{" +
                "billID='" + billID + '\'' +
                ", worker=" + worker +
                ", listOfItems=" + listOfItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartOrReceipt that = (CartOrReceipt) o;

        return getBillID().equals(that.getBillID());
    }
}
