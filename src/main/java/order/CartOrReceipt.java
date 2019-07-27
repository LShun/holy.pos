package order;

import food_menu.Product;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public abstract class CartOrReceipt {
    protected String billID;
    protected String staffID;
    protected LocalDateTime transactionTime;
    protected HashMap<Product, Integer> items = new HashMap<Product , Integer>();

    public String getBillID()                 { return billID; }
    public String getStaffID()                { return staffID; }
    public LocalDateTime getTransactionTime() { return transactionTime; }
    public HashMap<Product , Integer> getItems(){
        return items;
    }

    public void display() {
        String content;
        char[] horizontalLine = new char[48];
        Arrays.fill(horizontalLine,'\u2500');

        char[] upperHorizontal = Arrays.copyOf(horizontalLine,48);
        char[] lowerHorizontal = Arrays.copyOf(horizontalLine,48);
        char[] innerHorizontal = Arrays.copyOf(horizontalLine,48);
        upperHorizontal[25] = upperHorizontal[30] = upperHorizontal[39] = '\u252c';
        lowerHorizontal[25] = lowerHorizontal[30] = lowerHorizontal[39] = '\u2534';
        innerHorizontal[25] = innerHorizontal[30] = innerHorizontal[39] = '\u253c';

        System.out.println("\u250c"+String.valueOf(upperHorizontal)+"\u2510");
        content = String.format("\u2502%-25s\u2502%4s\u2502%8s\u2502%8s\u2502","Name","Qty","RM","RM");
        System.out.println(content);
        System.out.println("\u251c" + String.valueOf(innerHorizontal) + "\u2524");

        for (Map.Entry<Product, Integer> e : items.entrySet()) {
//            System.out.printf("%-25s%4d%8.2f|%7.2f\n", e.getKey().getTitle(), e.getValue(),
//                    e.getKey().getPrice(), e.getKey().getPrice() * e.getValue() );
            content = String.format("\u2502%-25s\u2502%4d\u2502%8.2f\u2502%8.2f\u2502", e.getKey().getTitle(), e.getValue(),
                    e.getKey().getPrice(), e.getKey().getPrice() * e.getValue());
            System.out.println(content);
        }

        System.out.println("\u251c" + String.valueOf(lowerHorizontal) + "\u2525");
        System.out.printf("\u2502%39s\u2502%8.2f\u2502\n","Total",100.50);
        System.out.println("\u2514" + String.valueOf(lowerHorizontal) + "\u2518");
    }
}
