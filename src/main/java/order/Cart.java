package order;

import java.time.*;
import java.util.HashMap;
import java.util.Map;
import food_menu.Product;

public class Cart{
    private String billID;
    private String staffID;
    private String customerID;
    private LocalDateTime transactionTime;
    private HashMap<Product , Integer> items;

//    public Cart(String staffID, String customerID) {
//        this.billID          = ID//generateID;
//        this.staffID         = //from Authentication;
//        this.customerID      = customerID;
//        this.transactionTime = LocalDateTime.now();
//        this.items           = new HashMap<Product , Integer>();
//    }

    public HashMap<Product , Integer> getItems(){
        return this.items;
    }

    public void addOrMinus(Product item, int qty){
        this.items.put(item, this.items.getOrDefault(item, 0) + qty);
    }

//    public void del(Product obj){
//        this.items.remove(obj);
//    }

    public void clearCart(){
        this.items.clear();
    }

    public double proceed(){
        double total = 0;
        for(Map.Entry<Product , Integer> e : items.entrySet()){
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }
}
