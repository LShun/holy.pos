package order;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import auth.Auth;
import food_menu.*;
import staff.Staff;

public class Cart extends CartOrReceipt{

    private double total;

    public Cart() {
        this.billID  = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%04d", super.transactionMade);
        this.staff   = Auth.s;
    }

    public Cart(String billID, Staff staff){
        this.billID      = billID;
        this.staff       = staff;
    }

    public void addOrMinus(Item item){
        int index = listOfItems.indexOf(item);

        if(index == -1)
            listOfItems.add(item);
        else
            listOfItems.get(index).setQty(item.getQty() + listOfItems.get(index).getQty());
        total = calculateTotal();
    }

    public void del(Product obj){
        listOfItems.remove(obj);
        total = calculateTotal();
    }

    public void clearCart(){
        listOfItems.clear();
        total = 0;
    }

    public double calculateTotal(){
        double total = 0;
        for(Item e : listOfItems){
            total += e.getProduct().getPrice() * e.getQty();
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
        for (Item e : listOfItems) {
            content = String.format("\u2502%4d\u2502%-6s\u2502%-25s\u2502%3d\u2502%7.2f\u2502%8.2f\u2502",
                    i++, e.getProduct().getId(), e.getProduct().getTitle(), e.getQty(),
                    e.getProduct().getPrice(), e.getProduct().getPrice() * e.getQty());
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
