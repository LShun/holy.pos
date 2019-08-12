package order;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import auth.AuthV2;
import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import staff.Worker;

public class Cart extends CartOrReceipt{

    private double subTotal;

    public Cart() {
        this.billID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd")) + String.format("%04d", super.transactionMade);
        this.worker = AuthV2.getSession();
    }

    public Cart(String billID, Worker worker){
        this.billID  = billID;
        this.worker = worker;
    }

    public Cart(String billID, Worker worker, ArrayList<Item> item){
        this(billID, worker);
        this.listOfItems = item;
    }

    public void addOrMinus(Item item){
        int index       = listOfItems.indexOf(item);
        int qtyInParam  = item.getQty();

        if(index != -1) {
            int qtyInList = listOfItems.get(index).getQty();
            listOfItems.get(index).setQty(qtyInParam + qtyInList);
        }else if(qtyInParam > 0) {
            listOfItems.add(item);
        }else{
            System.out.println("Invalid input quantity!");
        }
        subTotal = calculateSubTotal();
    }

    public boolean del(Item obj){
        boolean ans = listOfItems.remove(obj);
        subTotal = calculateSubTotal();

        return ans;
    }

    public void clearCart(){
        listOfItems.clear();
        subTotal = 0;
    }

    public double calculateSubTotal(){
        double subTotal = 0;
        for(Item e : listOfItems){
            subTotal += e.getProduct().getPrice() * e.getQty();
        }
        return subTotal;
    }

    public void display(){
//        String content;
//        char[] horizontalLine = new char[58];
//        Arrays.fill(horizontalLine,'\u2500');
//
//        char[] horizontal = Arrays.copyOf(horizontalLine,58);
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u252c';
//
//        System.out.println("\u250c"+String.valueOf(horizontal)+"\u2510");
//        content = String.format("\u2502%4s\u2502%-6s\u2502%-25s\u2502%3s\u2502%7s\u2502%8s\u2502","No.","ID","Name","Qty","RM","RM");
//        System.out.println(content);
//
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u253c';
//        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");
//
//        int i = 1;
//        for (Item e : listOfItems) {
//            content = String.format("\u2502%4d\u2502%-6s\u2502%-25s\u2502%3d\u2502%7.2f\u2502%8.2f\u2502",
//                    i++, e.getProduct().getId(), e.getProduct().getTitle(), e.getQty(),
//                    e.getProduct().getPrice(), e.getProduct().getPrice() * e.getQty());
//            System.out.println(content);
//        }
//
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41]  = '\u2534';
//        horizontal[49] = '\u253c';
//        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");
//        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Total", this.getSubTotal());
//
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = '\u2500';
//        horizontal[49] = '\u2534';
//
//        System.out.println("\u2514" + String.valueOf(horizontal) + "\u2518");
        AsciiTable at = new AsciiTable();
        CWC_FixedWidth width = new CWC_FixedWidth();
        at.getRenderer().setCWC(width);
        width.add(4).add(6).add(25).add(3).add(7).add(8); //Specify the width of each column
        at.addRule();

        AT_Row heading = at.addRow("No.","ID","Name","Qty","RM","RM");
        heading.setTextAlignment(TextAlignment.CENTER);
        at.addRule();

        int i = 1;
        for (Item e : listOfItems) {
            AT_Row row = at.addRow(i, e.getProduct().getId(), e.getProduct().getTitle(), e.getQty(),
                    e.getProduct().getPrice(), e.getProduct().getPrice() * e.getQty());

            //Alignment
            row.getCells().get(0).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(3).getContext().setTextAlignment(TextAlignment.CENTER);
            row.getCells().get(4).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(5).getContext().setTextAlignment(TextAlignment.RIGHT);

            i++;
        }
        at.addRule();
        System.out.println(at.render());

    }

    public void setBillID(String billID) { this.billID = billID; }

    public double getSubTotal() { return subTotal; }
}
