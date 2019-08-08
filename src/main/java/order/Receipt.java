package order;

import staff.Worker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Receipt extends CartOrReceipt {
    final private double total;
    final private LocalDateTime transactionTime;
    final private double amountReceived;

    public Receipt(Cart c, double amountReceived){
        this(c, amountReceived, LocalDateTime.now());
    }

    public Receipt(String billID, Worker worker, ArrayList<Item> listOfItems, double total, double amountReceived, LocalDateTime transactionTime){
        this.billID = billID;
        this.worker = worker;
        this.listOfItems = listOfItems;
        this.total = total;
        this.amountReceived = amountReceived;
        this.transactionTime = transactionTime;
    }

    public Receipt(Cart c, double amountReceived, LocalDateTime transactionTime){
        this.billID          = c.getBillID();
        this.worker          = c.getWorker();
        this.transactionTime = transactionTime;
        this.listOfItems     = c.getListOfItems();
        this.total           = c.getTotal();
        this.amountReceived  = amountReceived;
        super.transactionMade++;
    }

    public double getTotal(){ return total;}
    public double getAmountReceived() { return amountReceived; }
    public LocalDateTime getTransactionTime() { return transactionTime; }

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
        System.out.printf("\u2502%-16s:%-41s\u2502\n", "Transaction Time", getTransactionTime().toString());
        System.out.printf("\u2502%-16s:%15s%26c\u2502\n", "Employee", worker.getName(),' ');

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u252c';
        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");

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
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Total", total);
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Amount Received", amountReceived);
        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Change", amountReceived - total);

        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = '\u2500';
        horizontal[49] = '\u2534';

        System.out.println("\u2514" + String.valueOf(horizontal) + "\u2518");

    }


}
