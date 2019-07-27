package order;

import food_menu.Product;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class Receipt extends CartOrReceipt {
    final private double total;
    final private LocalDateTime transactionTime;
    final private double amountReceived;

    public Receipt(Cart c, double amountReceived){
        this.billID          = c.getBillID();
        this.staff           = c.getStaff();
        this.transactionTime = LocalDateTime.now();
        this.items           = c.getItems();
        this.total           = c.getTotal();
        this.amountReceived  = amountReceived;
        super.transactionMade++;
    }

    public double getTotal(){ return total;}
    public double getAmountReceived() { return amountReceived; }

    public void display(){
        String content;
        char[] horizontalLine = new char[58];
        Arrays.fill(horizontalLine,'\u2500');

        char[] horizontal = Arrays.copyOf(horizontalLine,58);

        System.out.println("\u250c"+String.valueOf(horizontal)+"\u2510");

        System.out.print(
                "\u2502 ______  ________________  __     _______________________ \u2502\n" +
                "\u2502 ___/ / / /_  __ \\__  /_ \\/ /     ___  __ \\_  __ \\_  ___/ \u2502\n" +
                "\u2502 __/ /_/ /_  / / /_  / __  /________  /_/ /  / / /____ \\  \u2502\n" +
                "\u2502 _/ __  / / /_/ /_  /___  /_/_____/  ____// /_/ /____/ /  \u2502\n" +
                "\u2502 /_/ /_/  \\____/ /_____/_/        /_/     \\____/ /____/   \u2502\n"+
                String.format("\u2502%58c\u2502\n", ' ')
        );
        System.out.printf("\u2502%-16s:%-41s\u2502\n", "Transaction Time", transactionTime.toString());
        System.out.printf("\u2502%-16s:%15s%26c\u2502\n", "Employee", staff.getName(),' ');

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u252c';
        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");

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
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Total", total);
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Amount Received", amountReceived);
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Change", amountReceived - total);

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = '\u2500';
        horizontal[49] = '\u2534';

        System.out.println("\u2514" + String.valueOf(horizontal) + "\u2518");

    }
}
