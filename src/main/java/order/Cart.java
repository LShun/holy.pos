package order;

import java.time.*;
import java.util.Map;

import auth.Auth;
import food_menu.*;

public class Cart extends CartOrReceipt{

    public Cart() {
        this.billID          = "0001";
        this.staffID         = Auth.s.getStaffID();
        this.transactionTime = LocalDateTime.now();
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

    public double proceed(){
        double total = 0;
        for(Map.Entry<Product , Integer> e : items.entrySet()){
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public void setBillID(String billID) { this.billID = billID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }
    public void setTransactionTime(LocalDateTime transactionTime) { this.transactionTime = transactionTime; }
}
