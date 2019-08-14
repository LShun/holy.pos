package order;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import de.vandermeer.asciitable.AsciiTable;

import menu.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt extends CartOrReceipt {
    final private BigDecimal subTotal;
    final private BigDecimal tax;
    final private BigDecimal total;
    final private BigDecimal amountReceived;
    final private LocalDateTime transactionTime;

    public Receipt(Cart c, double amountReceived){
        this(c, amountReceived, LocalDateTime.now());
    }

    public Receipt(Cart c, double amountReceived, LocalDateTime transactionTime){
        this.billID          = c.getBillID();
        this.worker          = c.getWorker();
        this.transactionTime = transactionTime;
        this.listOfItems     = c.getListOfItems();
        this.subTotal        = new BigDecimal(c.calculateSubTotal()).setScale(2, RoundingMode.HALF_EVEN);
        this.tax             = new BigDecimal(subTotal.doubleValue() * Product.getTax()).setScale(2, RoundingMode.HALF_EVEN);
        this.total           = subTotal.add(tax);
        this.amountReceived  = new BigDecimal(amountReceived).setScale(2, RoundingMode.HALF_EVEN);
        super.transactionMade++;
    }

    public BigDecimal getSubTotal(){ return subTotal;}
    public BigDecimal getAmountReceived() { return amountReceived; }
    public LocalDateTime getTransactionTime() { return transactionTime; }

    public void display(){
//        String content;
//        char[] horizontalLine = new char[58];
//        Arrays.fill(horizontalLine,'\u2500');
//
//        char[] horizontal = Arrays.copyOf(horizontalLine,58);
//
//        System.out.println("\u250c"+String.valueOf(horizontal)+"\u2510");
//
//        System.out.print(
//                "\u2502 ______  ________________  __     _______________________ \u2502\n" +
//                "\u2502 ___/ / / /_  __ \\__  /_ \\/ /     ___  __ \\_  __ \\_  ___/ \u2502\n" +
//                "\u2502 __/ /_/ /_  / / /_  / __  /________  /_/ /  / / /____ \\  \u2502\n" +
//                "\u2502 _/ __  / / /_/ /_  /___  /_/_____/  ____// /_/ /____/ /  \u2502\n" +
//                "\u2502 /_/ /_/  \\____/ /_____/_/        /_/     \\____/ /____/   \u2502\n"+
//                String.format("\u2502%58c\u2502\n", ' ')
//        );
//        System.out.printf("\u2502%-16s:%-41s\u2502\n", "Transaction Time", getTransactionTime().toString());
//        System.out.printf("\u2502%-16s:%15s%26c\u2502\n", "Employee", worker.getName(),' ');
//
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = horizontal[49] = '\u252c';
//        System.out.println("\u251c" + String.valueOf(horizontal) + "\u2524");
//
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
//        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Total", subTotal);
//        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Amount Received", amountReceived);
//        System.out.printf("\u2502%49s\u2502%8.2f\u2502\n","Change", amountReceived - subTotal);
//
//        horizontal[4] = horizontal[11] = horizontal[37] = horizontal[41] = '\u2500';
//        horizontal[49] = '\u2534';
//
//        System.out.println("\u2514" + String.valueOf(horizontal) + "\u2518");

        AsciiTable at = new AsciiTable();
        AT_Row row;
        CWC_FixedWidth width = new CWC_FixedWidth();
        at.getRenderer().setCWC(width);
        width.add(4).add(6).add(25).add(3).add(7).add(8); //Specify the width of each column

        at.addRule();

        at.addRow(null, null, "Transaction Time", null, null, transactionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss")));
        at.addRow(null, null, "Staff", null, null, worker.getName());

        at.addRule();
        at.addRow("No.","ID","Name","Qty","RM","RM");
        at.addRule();

        int i = 1;
        for (Item e : listOfItems) {
            row = at.addRow(i, e.getProduct().getId(), e.getProduct().getTitle(), e.getQty(),
                    e.getProduct().getPrice(), e.getProduct().getPrice() * e.getQty());

            //Alignment
            row.getCells().get(0).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(3).getContext().setTextAlignment(TextAlignment.CENTER);
            row.getCells().get(4).getContext().setTextAlignment(TextAlignment.RIGHT);
            row.getCells().get(5).getContext().setTextAlignment(TextAlignment.RIGHT);

            at.addRule();
            i++;
        }

        row = at.addRow(null,null,null,null,"SUBTOTAL", String.format("%.2f",subTotal));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null,null,null,null,"TAX", String.format("%.2f",tax));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null,null,null,null,"TOTAL", String.format("%.2f",total));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null,null,null,null,"CASH", String.format("%.2f",amountReceived));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null,null,null,null,"CHANGE", String.format("%.2f", amountReceived.subtract(total)));
        row.setTextAlignment(TextAlignment.RIGHT);
        at.addRule();


        System.out.println(at.render());
    }


    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
