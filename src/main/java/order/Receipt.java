package order;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_FixedWidth;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;
import product_menu.Product;

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

    public Receipt(Cart c, double amountReceived) {
        this(c, amountReceived, LocalDateTime.now());
    }

    public Receipt(Cart c, double amountReceived, LocalDateTime transactionTime) {
        this.billID = c.getBillID();
        this.worker = c.getWorker();
        this.transactionTime = transactionTime;
        this.listOfItems = c.getListOfItems();
        this.subTotal = new BigDecimal(c.calculateSubTotal()).setScale(2, RoundingMode.HALF_EVEN);
        this.tax = new BigDecimal(subTotal.doubleValue() * Product.getTax()).setScale(2, RoundingMode.HALF_EVEN);
        this.total = subTotal.add(tax);
        this.amountReceived = new BigDecimal(amountReceived).setScale(2, RoundingMode.HALF_EVEN);
        super.transactionMade++;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void display() {
        AsciiTable at = new AsciiTable();
        AT_Row row;
        CWC_FixedWidth width = new CWC_FixedWidth();
        at.getRenderer().setCWC(width);
        width.add(4).add(6).add(25).add(3).add(7).add(8); //Specify the width of each column

        at.addRule();

        at.addRow(null, null, "Transaction Time", null, null, transactionTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss")));
        at.addRow(null, null, "Staff", null, null, worker.getName());

        at.addRule();
        at.addRow("No.", "ID", "Name", "Qty", "RM", "RM");
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

        row = at.addRow(null, null, null, null, "SUBTOTAL", String.format("%.2f", subTotal));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null, null, null, null, "TAX", String.format("%.2f", tax));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null, null, null, null, "TOTAL", String.format("%.2f", total));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null, null, null, null, "CASH", String.format("%.2f", amountReceived));
        row.setTextAlignment(TextAlignment.RIGHT);
        row = at.addRow(null, null, null, null, "CHANGE", String.format("%.2f", amountReceived.subtract(total)));
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
