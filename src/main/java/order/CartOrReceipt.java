package order;

import food_menu.Product;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class CartOrReceipt {
    protected String billID;
    protected String staffID;
    protected String customerID;
    protected LocalDateTime transactionTime;
    protected HashMap<Product, Integer> items = new HashMap<Product , Integer>();

    public String getBillID()                 { return billID; }
    public String getStaffID()                { return staffID; }
    public String getCustomerID()             { return customerID; }
    public LocalDateTime getTransactionTime() { return transactionTime; }
    public HashMap<Product , Integer> getItems(){
        return items;
    }

    public void display() {
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.printf("%-8s%-15s%3d\n",e.getKey().getId(), e.getKey().getTitle(), e.getValue());
        }
    }
}
