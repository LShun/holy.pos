package order;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import food_menu.*;

public class Cart{
    private String billID;
    private String staffID;
    private String customerID;
    private LocalDateTime transactionTime;
    private HashMap<Product , Integer> items = new HashMap<Product , Integer>();

//    public Cart(String staffID, String customerID) {
//        this.billID          = ID//generateID;
//        this.staffID         = //from Authentication;
//        this.customerID      = customerID;
//        this.transactionTime = LocalDateTime.now();
//    }

    public HashMap<Product , Integer> getItems(){
        return this.items;
    }

    public void addOrMinus(Product item, int qty){
        items.put(item, items.getOrDefault(item, 0) + qty);
    }

    public void del(Product obj){
        items.remove(obj);
    }

    public void clearCart(){
        items.clear();
    }

    public void display() {
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            System.out.println(e.getKey().getId() + "    " + e.getValue());
        }
    }

    public double proceed(){
        double total = 0;
        for(Map.Entry<Product , Integer> e : items.entrySet()){
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public String getBillID() { return billID; }
    public void setBillID(String billID) { this.billID = billID; }
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public String getCustomerID() { return customerID; }
    public void setCustomerID(String customerID) { this.customerID = customerID; }
    public LocalDateTime getTransactionTime() { return transactionTime; }
    public void setTransactionTime(LocalDateTime transactionTime) { this.transactionTime = transactionTime; }
}
