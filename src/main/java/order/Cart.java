package order;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

import auth.Auth;
import food_menu.*;


public class Cart extends CartOrReceipt{

    private double total;

    public Cart() {
        this.billID  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%04d", super.transactionMade);
        this.staff   = Auth.s;
    }

    public void addOrMinus(Product item, int qty){
        if(items.getOrDefault(item, 0) + qty <= 0)
            return;
        else
            items.put(item, items.getOrDefault(item, 0) + qty );
        total = calculateTotal();
    }

    public void del(Product obj){
        items.remove(obj);
        total = calculateTotal();
    }

    public void clearCart(){
        items.clear();
        total = 0;
    }

    public double calculateTotal(){
        double total = 0;
        for(Map.Entry<Product , Integer> e : items.entrySet()){
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public void display(){
        String content;
        char[] horizontalLine = new char[58];
        Arrays.fill(horizontalLine,'\u2500');

        char[] horizontal = Arrays.copyOf(horizontalLine,58);
        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u252c';

        System.out.println("\u250c"+String.valueOf(horizontal)+"\u2510");
        content = String.format("\u2502%4s\u2502%-6s\u2502%-25s\u2502%3s\u2502%7s\u2502%8s\u2502","No.","ID","Name","Qty","RM","RM");
        System.out.println(content);

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u253c';
        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");

        int i = 1;
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            content = String.format("\u2502%4d\u2502%-6s\u2502%-25s\u2502%3d\u2502%7.2f\u2502%8.2f\u2502",
                    i++, e.getKey().getId(), e.getKey().getTitle(), e.getValue(),
                    e.getKey().getPrice(), e.getKey().getPrice() * e.getValue());
            System.out.println(content);
        }

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41]  = '\u2534';
        horizontal[49] = '\u253c';
        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Total", this.getTotal());

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = '\u2500';
        horizontal[49] = '\u2534';

        System.out.println("\u2514" + String.valueOf(horizontal) + "\u2518");
    }

    public void setBillID(String billID) { this.billID = billID; }

    public double getTotal() { return total; }
}
